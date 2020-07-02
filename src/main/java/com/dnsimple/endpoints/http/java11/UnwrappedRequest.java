package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.SimpleResponse;

import java.io.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.*;

/**
 * Request class to be used when the API's response doesn't wrap it's payload in
 * a <code>{"data": ...}</code> JSON object and, instead, we need to assume the whole
 * JSON object in the response is what we should provide inside a SimpleResponse
 */
public class UnwrappedRequest<T> implements Request<SimpleResponse<T>, T> {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public UnwrappedRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    @Override
    public SimpleResponse<T> execute(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<T>> response = client.send(request, new JsonUnwrappedResponseHandler<>(dataType));
        checkStatusCode(response);
        SimpleResponse<T> apiResponse = new SimpleResponse<>(response.body().get());
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<W>> fromJsonUnwrapped(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplyUnwrappedResponse(inputStream, typeParam));
    }

    public static <W> Supplier<W> supplyUnwrappedResponse(InputStream inputStream, Class<W> typeParam) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                return gson.fromJson(br, typeParam);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public static class JsonUnwrappedResponseHandler<T> implements HttpResponse.BodyHandler<Supplier<T>> {
        private final Class<T> typeParam;

        public JsonUnwrappedResponseHandler(Class<T> typeParam) {
            this.typeParam = typeParam;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<T>> apply(HttpResponse.ResponseInfo responseInfo) {
            return responseInfo.statusCode() != 204 ? fromJsonUnwrapped(typeParam) : asTypedEmptyUnwrappedResponse();
        }
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<T>> asTypedEmptyUnwrappedResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> () -> (T) null);
    }
}

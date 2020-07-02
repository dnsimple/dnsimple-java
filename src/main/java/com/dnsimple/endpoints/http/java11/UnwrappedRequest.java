package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;

import java.io.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.*;

public class UnwrappedRequest {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public UnwrappedRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    <T> T requestUnwrappedWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, options, attributes, method, userAgent, accessToken);
        HttpResponse<Supplier<T>> response = client.send(request, new JsonUnwrappedResponseHandler<>(c));
        checkStatusCode(response);
        return response.body().get();
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

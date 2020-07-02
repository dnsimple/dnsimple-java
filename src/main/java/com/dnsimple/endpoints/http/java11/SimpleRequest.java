package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.SimpleResponse;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.*;

public class SimpleRequest {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public SimpleRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    <T> SimpleResponse<T> requestSimple(String path, Map<String, Object> options, String method, Class<T> typeParam, Object attributes) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, options, attributes, method, userAgent, accessToken);
        HttpResponse<Supplier<SimpleResponse<T>>> response = client.send(request, new JsonSimpleResponseHandler<>(typeParam));
        checkStatusCode(response);
        SimpleResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    <T> SimpleResponse<T> requestSimpleWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, options, attributes, method, userAgent, accessToken);
        HttpResponse<Supplier<SimpleResponse<T>>> response = client.send(request, new JsonSimpleResponseHandler<>(c));
        checkStatusCode(response);
        SimpleResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<SimpleResponse<W>>> fromJsonSimple(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplySimpleResponse(inputStream, typeParam));
    }

    public static <W> Supplier<SimpleResponse<W>> supplySimpleResponse(InputStream inputStream, Class<W> typeParam) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                Type listType = TypeToken.getParameterized(SimpleResponse.class, typeParam).getType();
                return gson.fromJson(br, listType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public static class JsonSimpleResponseHandler<T> implements HttpResponse.BodyHandler<Supplier<SimpleResponse<T>>> {
        private final Class<T> typeParam;

        public JsonSimpleResponseHandler(Class<T> typeParam) {
            this.typeParam = typeParam;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<SimpleResponse<T>>> apply(HttpResponse.ResponseInfo responseInfo) {
            return responseInfo.statusCode() != 204 ? fromJsonSimple(typeParam) : asTypedEmptySimpleResponse();
        }
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<SimpleResponse<T>>> asTypedEmptySimpleResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> SimpleResponse::empty);
    }
}

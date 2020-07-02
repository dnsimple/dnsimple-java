package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.PaginatedResponse;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.*;
import static java.util.Collections.emptyMap;

public class PageRequest {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public PageRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    <T> PaginatedResponse<T> requestPage(String path, Map<String, Object> options, String method, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, options, emptyMap(), method, userAgent, accessToken);
        HttpResponse<Supplier<PaginatedResponse<T>>> response = client.send(request, new JsonPaginatedResponseHandler<>(typeParam));
        checkStatusCode(response);
        PaginatedResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    public static <W> Supplier<PaginatedResponse<W>> supplyPaginatedResponse(InputStream inputStream, Class<W> typeParam) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                Type listType = TypeToken.getParameterized(PaginatedResponse.class, typeParam).getType();
                return gson.fromJson(br, listType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<PaginatedResponse<W>>> fromJsonPage(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplyPaginatedResponse(inputStream, typeParam));
    }

    public static class JsonPaginatedResponseHandler<T> implements HttpResponse.BodyHandler<Supplier<PaginatedResponse<T>>> {
        private final Class<T> typeParam;

        public JsonPaginatedResponseHandler(Class<T> typeParam) {
            this.typeParam = typeParam;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<PaginatedResponse<T>>> apply(HttpResponse.ResponseInfo responseInfo) {
            return responseInfo.statusCode() != 204 ? fromJsonPage(typeParam) : asTypedEmptyPaginatedResponse();
        }
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<PaginatedResponse<T>>> asTypedEmptyPaginatedResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> PaginatedResponse::empty);
    }
}

package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListResponse;
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

public class ListRequest {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public ListRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    <T> ListResponse<T> requestList(String path, Map<String, Object> options, String method, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, options, emptyMap(), method, userAgent, accessToken);
        HttpResponse<Supplier<ListResponse<T>>> response = client.send(request, new JsonListResponseHandler<>(typeParam));
        checkStatusCode(response);
        ListResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    <T> ListResponse<T> requestListWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, options, attributes, method, userAgent, accessToken);
        HttpResponse<Supplier<ListResponse<T>>> response = client.send(request, new JsonListResponseHandler<>(c));
        checkStatusCode(response);
        ListResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<ListResponse<W>>> fromJsonList(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplyListResponse(inputStream, typeParam));
    }

    public static <W> Supplier<ListResponse<W>> supplyListResponse(InputStream inputStream, Class<W> typeParam) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                Type listType = TypeToken.getParameterized(ListResponse.class, typeParam).getType();
                return gson.fromJson(br, listType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public static class JsonListResponseHandler<T> implements HttpResponse.BodyHandler<Supplier<ListResponse<T>>> {
        private final Class<T> typeParam;

        public JsonListResponseHandler(Class<T> typeParam) {
            this.typeParam = typeParam;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<ListResponse<T>>> apply(HttpResponse.ResponseInfo responseInfo) {
            return responseInfo.statusCode() != 204 ? fromJsonList(typeParam) : asTypedEmptyListResponse();
        }
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<ListResponse<T>>> asTypedEmptyListResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> ListResponse::empty);
    }
}

package com.dnsimple.endpoints.http;

import com.dnsimple.request.Filter;
import com.dnsimple.response.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.dnsimple.endpoints.http.HttpRequestFactory.API_VERSION_PATH;
import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpEndpointClient {
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final HttpRequestFactory requestFactory;
    private final String apiBase;
    private String userAgent;
    private String accessToken;

    public HttpEndpointClient(HttpRequestFactory requestFactory, String apiBase) {
        this.requestFactory = requestFactory;
        this.apiBase = apiBase;
    }

    public String getApiBase() {
        return apiBase;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    EmptyResponse empty(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body) {
        return execute(userAgent, accessToken, method, path, queryStringParams, body, Void.class, EmptyResponse.class, EmptyResponse::new);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> SimpleResponse<DATA_TYPE> simple(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, queryStringParams, body, dataType, SimpleResponse.class, SimpleResponse::empty);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> ListResponse<DATA_TYPE> list(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, queryStringParams, body, dataType, ListResponse.class, ListResponse::empty);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> PaginatedResponse<DATA_TYPE> page(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, queryStringParams, body, dataType, PaginatedResponse.class, PaginatedResponse::empty);
    }

    <DATA_TYPE> DATA_TYPE raw(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, queryStringParams, body, dataType);
    }

    private <DATA_TYPE, CONTAINER extends ApiResponse> CONTAINER execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier) {
        URI uri = buildUrl(apiBase, API_VERSION_PATH, path, queryStringParams);
        RawResponse response = requestFactory.execute(userAgent, accessToken, method, uri, queryStringParams, body);
        return response.getStatusCode() != 204
                ? deserializeContainer(response.getBody(), dataType, containerType)
                : emptyContainerSupplier.get();
    }

    private <DATA_TYPE> DATA_TYPE execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) {
        URI uri = buildUrl(apiBase, API_VERSION_PATH, path, queryStringParams);
        RawResponse response = requestFactory.execute(userAgent, accessToken, method, uri, queryStringParams, body);
        return response.getStatusCode() != 204 ? deserialize(response.getBody(), dataType) : null;
    }

    private static <CONTAINER extends ApiResponse, DATA_TYPE> CONTAINER deserializeContainer(InputStream inputStream, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType) {
        try (InputStream stream = inputStream;
             InputStreamReader isr = new InputStreamReader(stream);
             BufferedReader br = new BufferedReader(isr)) {
            return gson.fromJson(br, TypeToken.getParameterized(containerType, dataType).getType());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static <DATA_TYPE> DATA_TYPE deserialize(InputStream inputStream, Class<DATA_TYPE> dataType) {
        try (InputStream stream = inputStream;
             InputStreamReader isr = new InputStreamReader(stream);
             BufferedReader br = new BufferedReader(isr)) {
            return gson.fromJson(br, dataType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static URI buildUrl(String apiBase, String versionPath, String requestedPath, Map<String, Object> queryStringParams) {
        List<String> queryStringItems = new ArrayList<String>();
        if (queryStringParams.containsKey("filter")) {
            Filter filter = (Filter) queryStringParams.remove("filter");
            queryStringItems.add(filter.name + "=" + URLEncoder.encode(filter.value, UTF_8));
        }
        queryStringItems.addAll(queryStringParams.entrySet().stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), UTF_8))
                .collect(Collectors.toList()));
        return URI.create(apiBase + versionPath + requestedPath + (queryStringItems.isEmpty() ? "" : ("?" + String.join("&", queryStringItems))));
    }
}

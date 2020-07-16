package com.dnsimple.http;

import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.function.Supplier;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

public class HttpEndpointClient {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeDeserializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .create();
    private static final String API_VERSION_PATH = "/v2/";
    private final HttpRequestFactory requestFactory;
    private final URL apiBase;
    private final String userAgent;
    private Optional<String> accessToken;

    public HttpEndpointClient(HttpRequestFactory requestFactory, URL apiBase, String userAgent, Optional<String> accessToken) {
        this.requestFactory = requestFactory;
        this.apiBase = apiBase;
        this.userAgent = userAgent;
        this.accessToken = accessToken;
    }

    public URL getApiBase() {
        return apiBase;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = Optional.of(accessToken);
    }

    public EmptyResponse empty(HttpMethod method, String path, ListOptions options, Object body) {
        return execute(userAgent, accessToken, method, path, options, body, Void.class, EmptyResponse.class, EmptyResponse::new);
    }

    @SuppressWarnings("unchecked")
    public <DATA_TYPE> SimpleResponse<DATA_TYPE> simple(HttpMethod method, String path, ListOptions options, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, options, body, dataType, SimpleResponse.class, SimpleResponse::empty);
    }

    @SuppressWarnings("unchecked")
    public <DATA_TYPE> ListResponse<DATA_TYPE> list(HttpMethod method, String path, ListOptions options, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, options, body, dataType, ListResponse.class, ListResponse::empty);
    }

    @SuppressWarnings("unchecked")
    public <DATA_TYPE> PaginatedResponse<DATA_TYPE> page(HttpMethod method, String path, ListOptions options, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, options, body, dataType, PaginatedResponse.class, PaginatedResponse::empty);
    }

    public <DATA_TYPE> DATA_TYPE raw(HttpMethod method, String path, ListOptions options, Object body, Class<DATA_TYPE> dataType) {
        return execute(userAgent, accessToken, method, path, options, body, dataType);
    }

    private <DATA_TYPE, CONTAINER> CONTAINER execute(String userAgent, Optional<String> accessToken, HttpMethod method, String path, ListOptions options, Object body, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier) {
        URI uri = buildUrl(apiBase, API_VERSION_PATH, path, options);
        RawResponse response = requestFactory.execute(userAgent, accessToken, method, uri, body);
        return response.getStatusCode() != 204
                ? deserializeContainer(response.getBody(), dataType, containerType)
                : emptyContainerSupplier.get();
    }

    private <DATA_TYPE> DATA_TYPE execute(String userAgent, Optional<String> accessToken, HttpMethod method, String path, ListOptions options, Object body, Class<DATA_TYPE> dataType) {
        URI uri = buildUrl(apiBase, API_VERSION_PATH, path, options);
        RawResponse response = requestFactory.execute(userAgent, accessToken, method, uri, body);
        return response.getStatusCode() != 204 ? deserialize(response.getBody(), dataType) : null;
    }

    private static <CONTAINER, DATA_TYPE> CONTAINER deserializeContainer(InputStream inputStream, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType) {
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

    private static URI buildUrl(URL apiBase, String versionPath, String requestedPath, ListOptions options) {
        return URI.create(apiBase + versionPath + requestedPath + options.asQueryString());
    }
}

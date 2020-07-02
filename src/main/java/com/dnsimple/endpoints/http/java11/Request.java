package com.dnsimple.endpoints.http.java11;

import com.dnsimple.Dnsimple;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyMap;

class Request {
    private static final String API_VERSION_PATH = "/v2/";
    private static final String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    Request(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    EmptyResponse empty(String path, Object body, Map<String, Object> queryStringParams, HttpMethod method) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, Void.class, EmptyResponse.class, EmptyResponse::new, method);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> SimpleResponse<DATA_TYPE> simple(String path, Object body, Map<String, Object> queryStringParams, HttpMethod method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, SimpleResponse.class, SimpleResponse::empty, method);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> ListResponse<DATA_TYPE> list(HttpMethod method, String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, ListResponse.class, ListResponse::empty, method);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> PaginatedResponse<DATA_TYPE> page(String path, Object body, Map<String, Object> queryStringParams, HttpMethod method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, PaginatedResponse.class, PaginatedResponse::empty, method);
    }

    <DATA_TYPE> DATA_TYPE raw(String path, Object body, Map<String, Object> queryStringParams, HttpMethod method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<DATA_TYPE>> response = client.send(request, new JsonResponseHandler<>(dataType));
        checkStatusCode(response);
        return response.body().get();
    }

    // We need to define a TYPED_CONTAINER instead of at interface level because otherwise, we won't be able to provide a valid containerType argument
    private static <DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> CONTAINER execute(HttpClient client, String userAgent, String accessToken, String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier, HttpMethod method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<CONTAINER>> response = client.send(request, new JsonContainerResponseHandler<>(dataType, containerType, emptyContainerSupplier));
        checkStatusCode(response);
        CONTAINER apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private static HttpRequest buildRequest(String path, Map<String, Object> options, Object attributes, HttpMethod method, String userAgent, String accessToken) {
        HttpRequest.BodyPublisher bodyPublisher = attributes != null
                ? HttpRequest.BodyPublishers.ofString(gson.toJson(attributes))
                : HttpRequest.BodyPublishers.noBody();
        return HttpRequest.newBuilder(buildUrl(Dnsimple.getApiBase() + API_VERSION_PATH + path, options))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents(userAgent)))
                .header("Authorization", "Bearer " + accessToken)
                .method(method.name(), bodyPublisher)
                .build();
    }

    private static List<String> buildUserAgents(String userAgent) {
        // Add the user agent string to the headers
        List<String> fullUserAgent = new ArrayList<>();
        if (userAgent != null)
            fullUserAgent.add(userAgent);
        fullUserAgent.add(DEFAULT_USER_AGENT);
        return fullUserAgent;
    }

    private static URI buildUrl(String url, Map<String, Object> options) {
        if (options == null)
            options = emptyMap();
        var queryStringItems = new ArrayList<String>();
        if (options.containsKey("filter")) {
            Filter filter = (Filter) options.remove("filter");
            queryStringItems.add(filter.name + "=" + URLEncoder.encode(filter.value, UTF_8));
        }
        queryStringItems.addAll(options.entrySet().stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), UTF_8))
                .collect(Collectors.toList()));
        return URI.create(url + (queryStringItems.isEmpty() ? "" : ("?" + String.join("&", queryStringItems))));
    }

    private static <CONTAINER extends ApiResponse<DATA_TYPE>, DATA_TYPE> Supplier<CONTAINER> buildSupplier(InputStream inputStream, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                return gson.fromJson(br, TypeToken.getParameterized(containerType, dataType).getType());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    private static <DATA_TYPE> Supplier<DATA_TYPE> buildSupplier(InputStream inputStream, Class<DATA_TYPE> dataType) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                return gson.fromJson(br, dataType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    private static void checkStatusCode(HttpResponse<?> response) throws DnsimpleException {
        int statusCode = response.statusCode();
        if (statusCode == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, statusCode);
        if (statusCode >= 500)
            throw new DnsimpleException("Got an error executing the request", null, statusCode);
        if (statusCode >= 400)
            throw new DnsimpleException("Wrong request", null, statusCode);
    }

    private static class JsonContainerResponseHandler<DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> implements HttpResponse.BodyHandler<Supplier<CONTAINER>> {
        private final Class<DATA_TYPE> dataType;
        private final Class<CONTAINER> containerType;
        private final Supplier<CONTAINER> emptyContainerSupplier;

        public JsonContainerResponseHandler(Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier) {
            this.dataType = dataType;
            this.containerType = containerType;
            this.emptyContainerSupplier = emptyContainerSupplier;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<CONTAINER>> apply(HttpResponse.ResponseInfo responseInfo) {
            HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
            return responseInfo.statusCode() != 204
                    ? HttpResponse.BodySubscribers.mapping(upstream, is -> buildSupplier(is, dataType, containerType))
                    : HttpResponse.BodySubscribers.mapping(upstream, __ -> emptyContainerSupplier);
        }
    }

    private static class JsonResponseHandler<DATA_TYPE> implements HttpResponse.BodyHandler<Supplier<DATA_TYPE>> {
        private final Class<DATA_TYPE> dataType;

        public JsonResponseHandler(Class<DATA_TYPE> dataType) {
            this.dataType = dataType;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<DATA_TYPE>> apply(HttpResponse.ResponseInfo responseInfo) {
            HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
            return responseInfo.statusCode() != 204
                    ? HttpResponse.BodySubscribers.mapping(upstream, is -> buildSupplier(is, dataType))
                    : HttpResponse.BodySubscribers.mapping(upstream, __ -> () -> null);
        }
    }
}

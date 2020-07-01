package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NativeHttpEndpointClient implements HttpEndpointClient {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    public static final String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;
    private static final String API_VERSION_PATH = "/v2/";
    private final HttpClient client;
    private String accessToken;
    private String userAgent;

    public NativeHttpEndpointClient() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
    }

    @Override
    public <T> ListResponse<T> getList(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return requestList(path, options, "GET", typeParam);
    }

    @Override
    public <T> PaginatedResponse<T> getPage(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return requestPage(path, options, "GET", typeParam);
    }

    @Override
    public <T> SimpleResponse<T> getSimple(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return requestSimple(path, options, "GET", typeParam);
    }

    @Override
    public <T> SimpleResponse<T> postSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestSimpleWithBody(path, attributes, options, c, "POST");
    }

    @Override
    public <T> T postUnwrapped(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestUnwrappedWithBody(path, attributes, options, c, "POST");
    }

    @Override
    public EmptyResponse postEmpty(String path, Object attributes, Map<String, Object> options) throws IOException, InterruptedException, DnsimpleException {
        return requestEmptyWithBody(path, attributes, options, "POST");
    }

    @Override
    public <T> ListResponse<T> putList(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestListWithBody(path, attributes, options, c, "PUT");
    }

    @Override
    public <T> SimpleResponse<T> putSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestSimpleWithBody(path, attributes, options, c, "PUT");
    }

    @Override
    public EmptyResponse putEmpty(String path, Object attributes, Map<String, Object> options) throws IOException, InterruptedException, DnsimpleException {
        return requestEmptyWithBody(path, attributes, options, "PUT");
    }

    @Override
    public <T> SimpleResponse<T> patchSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestSimpleWithBody(path, attributes, options, c, "PATCH");
    }

    @Override
    public <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestSimple(path, options, "DELETE", c);
    }

    @Override
    public EmptyResponse deleteEmpty(String path, Map<String, Object> options) throws IOException, InterruptedException, DnsimpleException {
        return requestEmpty(path, options, "DELETE");
    }

    private EmptyResponse requestEmpty(String path, Map<String, Object> options, String method) throws IOException, InterruptedException, DnsimpleException {
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        EmptyResponse apiResponse = new EmptyResponse();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T> ListResponse<T> requestList(String path, Map<String, Object> options, String method, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Supplier<ListResponse<T>>> response = client.send(request, new JsonListResponseHandler<>(typeParam));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        ListResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T> PaginatedResponse<T> requestPage(String path, Map<String, Object> options, String method, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Supplier<PaginatedResponse<T>>> response = client.send(request, new JsonPaginatedResponseHandler<>(typeParam));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        PaginatedResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T> SimpleResponse<T> requestSimple(String path, Map<String, Object> options, String method, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Supplier<SimpleResponse<T>>> response = client.send(request, new JsonSimpleResponseHandler<>(typeParam));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        SimpleResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T> ListResponse<T> requestListWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        String requestBody = new Gson().toJson(attributes);
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<Supplier<ListResponse<T>>> response = client.send(request, new JsonListResponseHandler<>(c));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        ListResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T> SimpleResponse<T> requestSimpleWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        String requestBody = new Gson().toJson(attributes);
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<Supplier<SimpleResponse<T>>> response = client.send(request, new JsonSimpleResponseHandler<>(c));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        SimpleResponse<T> apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T> T requestUnwrappedWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        String requestBody = new Gson().toJson(attributes);
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<Supplier<T>> response = client.send(request, new JsonUnwrappedResponseHandler<>(c));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        return response.body().get();
    }

    private EmptyResponse requestEmptyWithBody(String path, Object attributes, Map<String, Object> options, String method) throws IOException, InterruptedException, DnsimpleException {
        String requestBody = new Gson().toJson(attributes);
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        EmptyResponse apiResponse = new EmptyResponse();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private List<String> buildUserAgents() {
        // Add the user agent string to the headers
        List<String> fullUserAgent = new ArrayList<>();
        if (userAgent != null)
            fullUserAgent.add(userAgent);
        fullUserAgent.add(DEFAULT_USER_AGENT);
        return fullUserAgent;
    }

    private String versionedPath(String path) {
        return Dnsimple.getApiBase() + API_VERSION_PATH + path;
    }

    private URI buildUrl(String url, Map<String, Object> options) {
        if (options == null)
            options = Collections.emptyMap();
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

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<ListResponse<W>>> fromJsonList(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplyListResponse(inputStream, typeParam));
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<PaginatedResponse<W>>> fromJsonPage(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplyPaginatedResponse(inputStream, typeParam));
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<SimpleResponse<W>>> fromJsonSimple(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplySimpleResponse(inputStream, typeParam));
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<W>> fromJsonUnwrapped(Class<W> typeParam) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> supplyUnwrappedResponse(inputStream, typeParam));
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

    private static <T> HttpResponse.BodySubscriber<Supplier<ListResponse<T>>> asTypedEmptyListResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> ListResponse::empty);
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<PaginatedResponse<T>>> asTypedEmptyPaginatedResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> PaginatedResponse::empty);
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<SimpleResponse<T>>> asTypedEmptySimpleResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> SimpleResponse::empty);
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<T>> asTypedEmptyUnwrappedResponse() {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> () -> (T) null);
    }
}

package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.ApiResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.mikael.urlbuilder.UrlBuilder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
    public <T extends ApiResponse> T get(String path, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return request(path, options, c, "GET");
    }

    @Override
    public <T extends ApiResponse> T post(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestWithBody(path, attributes, options, c, "POST");
    }

    @Override
    public <T extends ApiResponse> T put(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestWithBody(path, attributes, options, c, "PUT");
    }

    @Override
    public <T extends ApiResponse> T patch(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return requestWithBody(path, attributes, options, c, "PATCH");
    }

    @Override
    public <T extends ApiResponse> T delete(String path, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return request(path, options, c, "DELETE");
    }

    private <T extends ApiResponse> T request(String path, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException, ResourceNotFoundException {
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Supplier<T>> response = client.send(request, new JsonBodyHandler<>(c));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        T apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }

    private <T extends ApiResponse> T requestWithBody(String path, Object attributes, Map<String, Object> options, Class<T> c, String method) throws IOException, InterruptedException, DnsimpleException {
        String requestBody = new Gson().toJson(attributes);
        URI uri = buildUrl(versionedPath(path), options);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents()))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<Supplier<T>> response = client.send(request, new JsonBodyHandler<>(c));
        if (response.statusCode() == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, response.statusCode());
        if (response.statusCode() >= 500)
            throw new DnsimpleException("Got an error executing the request", null, response.statusCode());
        if (response.statusCode() >= 400)
            throw new DnsimpleException("Wrong request", null, response.statusCode());
        T apiResponse = response.body().get();
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
        if (options == null) {
            options = Collections.emptyMap();
        }
        UrlBuilder urlBuilder = UrlBuilder.fromString(url);
        if (options.containsKey("filter")) {
            Filter filter = (Filter) options.remove("filter");
            urlBuilder = urlBuilder.addParameter(filter.name, filter.value);
        }
        if (options.size() > 0) {
            for (Map.Entry<String, Object> kv : options.entrySet()) {
                urlBuilder = urlBuilder.addParameter(kv.getKey(), kv.getValue().toString());
            }
        }
        try {
            return urlBuilder.toUrl().toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, targetType));
    }

    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                return gson.fromJson(br, targetType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public static class JsonBodyHandler<T extends ApiResponse> implements HttpResponse.BodyHandler<Supplier<T>> {
        private final Class<T> wClass;

        public JsonBodyHandler(Class<T> wClass) {
            this.wClass = wClass;
        }

        @Override
        public HttpResponse.BodySubscriber<Supplier<T>> apply(HttpResponse.ResponseInfo responseInfo) {
            return responseInfo.statusCode() != 204 ? asJSON(wClass) : asTypedEmptyResponse(wClass);
        }
    }

    private static <T> HttpResponse.BodySubscriber<Supplier<T>> asTypedEmptyResponse(Class<T> wClass) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> () -> {
                    try {
                        return wClass.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException();
                    }
                }
        );
    }
}

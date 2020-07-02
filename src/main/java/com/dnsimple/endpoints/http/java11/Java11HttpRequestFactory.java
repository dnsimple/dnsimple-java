package com.dnsimple.endpoints.http.java11;

import com.dnsimple.Dnsimple;
import com.dnsimple.endpoints.http.HttpMethod;
import com.dnsimple.endpoints.http.HttpRequestFactory;
import com.dnsimple.exception.BadRequestException;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.exception.ServerError;
import com.dnsimple.request.Filter;
import com.dnsimple.response.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
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
import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Java11HttpRequestFactory implements HttpRequestFactory {
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final HttpClient client;

    public Java11HttpRequestFactory() {
        this.client = HttpClient.newBuilder().version(HTTP_1_1).followRedirects(ALWAYS).build();
    }

    @Override
    public <DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> CONTAINER execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier) {
        try {
            HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
            HttpResponse<Supplier<CONTAINER>> response = client.send(request, new Java11ContainerResponseHandler<>(dataType, containerType, emptyContainerSupplier));
            checkStatusCode(response);
            CONTAINER apiResponse = response.body().get();
            apiResponse.setHttpRequest(request);
            apiResponse.setHttpResponse(response);
            return apiResponse;
        } catch (IOException | InterruptedException e) {
            throw new DnsimpleException(e);
        }
    }

    @Override
    public <DATA_TYPE> DATA_TYPE execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) {
        try {
            HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
            HttpResponse<Supplier<DATA_TYPE>> response = client.send(request, new Java11RawResponseHandler<>(dataType));
            checkStatusCode(response);
            return response.body().get();
        } catch (IOException | InterruptedException e) {
            throw new DnsimpleException(e);
        }
    }

    private static HttpRequest buildRequest(String path, Map<String, Object> queryStringParams, Object attributes, HttpMethod method, String userAgent, String accessToken) {
        HttpRequest.BodyPublisher bodyPublisher = attributes != null
                ? HttpRequest.BodyPublishers.ofString(gson.toJson(attributes))
                : HttpRequest.BodyPublishers.noBody();
        return HttpRequest.newBuilder(buildUrl(Dnsimple.getApiBase() + API_VERSION_PATH + path, queryStringParams))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents(userAgent)))
                .header("Authorization", "Bearer " + accessToken)
                .method(method.name(), bodyPublisher)
                .build();
    }

    private static List<String> buildUserAgents(String userAgent) {
        List<String> fullUserAgent = new ArrayList<>();
        if (userAgent != null)
            fullUserAgent.add(userAgent);
        fullUserAgent.add(DEFAULT_USER_AGENT);
        return fullUserAgent;
    }

    private static URI buildUrl(String url, Map<String, Object> queryStringParams) {
        var queryStringItems = new ArrayList<String>();
        if (queryStringParams.containsKey("filter")) {
            Filter filter = (Filter) queryStringParams.remove("filter");
            queryStringItems.add(filter.name + "=" + URLEncoder.encode(filter.value, UTF_8));
        }
        queryStringItems.addAll(queryStringParams.entrySet().stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), UTF_8))
                .collect(Collectors.toList()));
        return URI.create(url + (queryStringItems.isEmpty() ? "" : ("?" + String.join("&", queryStringItems))));
    }

    private static void checkStatusCode(HttpResponse<?> response) throws DnsimpleException {
        int statusCode = response.statusCode();
        if (statusCode == 404)
            throw new ResourceNotFoundException();
        if (statusCode >= 500)
            throw new ServerError(statusCode);
        if (statusCode >= 400)
            throw new BadRequestException(statusCode);
    }
}

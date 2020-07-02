package com.dnsimple.endpoints.http.java11;

import com.dnsimple.Dnsimple;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyMap;

interface CommonRequest {
    String API_VERSION_PATH = "/v2/";
    String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;
    Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();

    static HttpRequest buildRequest(String path, Map<String, Object> options, Object attributes, String method, String userAgent, String accessToken) {
        HttpRequest.BodyPublisher bodyPublisher = attributes != null
                ? HttpRequest.BodyPublishers.ofString(gson.toJson(attributes))
                : HttpRequest.BodyPublishers.noBody();
        return HttpRequest.newBuilder(buildUrl(versionedPath(path), options))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", String.join(" ", buildUserAgents(userAgent)))
                .header("Authorization", "Bearer " + accessToken)
                .method(method, bodyPublisher)
                .build();
    }

    static List<String> buildUserAgents(String userAgent) {
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

    private static String versionedPath(String path) {
        return Dnsimple.getApiBase() + API_VERSION_PATH + path;
    }

    static void checkStatusCode(HttpResponse<?> response) throws DnsimpleException {
        int statusCode = response.statusCode();
        if (statusCode == 404)
            throw new ResourceNotFoundException("Failed to execute request", null, statusCode);
        if (statusCode >= 500)
            throw new DnsimpleException("Got an error executing the request", null, statusCode);
        if (statusCode >= 400)
            throw new DnsimpleException("Wrong request", null, statusCode);
    }
}

package com.dnsimple.endpoints.http.java11;

import com.dnsimple.Dnsimple;
import com.dnsimple.endpoints.http.HttpMethod;
import com.dnsimple.endpoints.http.HttpRequestFactory;
import com.dnsimple.endpoints.http.RawResponse;
import com.dnsimple.exception.BadRequestException;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.exception.ServerError;
import com.dnsimple.request.Filter;
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
    public RawResponse execute(String userAgent, String accessToken, HttpMethod method, URI uri, Map<String, Object> queryStringParams, Object body) {
        try {
            HttpRequest request = buildRequest(method, uri, body, userAgent, accessToken);
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            checkStatusCode(response);
            return new RawResponse(
                    response.statusCode(),
                    response.headers().map(),
                    response.body()
            );
        } catch (IOException | InterruptedException e) {
            throw new DnsimpleException(e);
        }
    }

    private static HttpRequest buildRequest(HttpMethod method, URI uri, Object attributes, String userAgent, String accessToken) {
        var bodyPublisher = attributes != null
                ? HttpRequest.BodyPublishers.ofString(gson.toJson(attributes))
                : HttpRequest.BodyPublishers.noBody();
        return HttpRequest.newBuilder(uri)
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

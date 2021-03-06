package com.dnsimple.http.java11;

import com.dnsimple.exception.BadRequestException;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.exception.ServerError;
import com.dnsimple.http.HttpMethod;
import com.dnsimple.http.HttpRequestFactory;
import com.dnsimple.http.RawResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;

public class Java11HttpRequestFactory implements HttpRequestFactory {
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final HttpClient client;

    public Java11HttpRequestFactory() {
        this.client = HttpClient.newBuilder().version(HTTP_1_1).followRedirects(ALWAYS).build();
    }

    @Override
    public RawResponse execute(String userAgent, Optional<String> accessToken, HttpMethod method, URI uri, Object body) {
        try {
            HttpRequest request = buildRequest(method, uri, body, userAgent, accessToken);
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
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

    private static HttpRequest buildRequest(HttpMethod method, URI uri, Object attributes, String userAgent, Optional<String> accessToken) {
        String body = gson.toJson(attributes);
        var bodyPublisher = attributes != null
                ? HttpRequest.BodyPublishers.ofString(body)
                : HttpRequest.BodyPublishers.noBody();
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("User-Agent", userAgent)
                .method(method.name(), bodyPublisher);
        return accessToken.map(token -> builder.header("Authorization", "Bearer " + token))
                .orElse(builder)
                .build();
    }

    private static void checkStatusCode(HttpResponse<InputStream> response) throws DnsimpleException {
        int statusCode = response.statusCode();
        if (statusCode == 404)
            throw new ResourceNotFoundException();
        if (statusCode >= 500)
            throw new ServerError(statusCode);
        if (statusCode >= 400) {
            Map<String, Object> body;
            try (InputStream stream = response.body();
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                Type empMapType = new TypeToken<Map<String, Object>>() {
                }.getType();
                body = gson.fromJson(br, empMapType);
            } catch (IOException e) {
                body = Collections.emptyMap();
            }
            throw new BadRequestException(statusCode, body);
        }
    }
}

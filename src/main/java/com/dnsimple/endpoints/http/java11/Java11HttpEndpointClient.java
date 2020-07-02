package com.dnsimple.endpoints.http.java11;

import com.dnsimple.endpoints.http.HttpEndpointClient;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.util.Collections.emptyMap;

public class Java11HttpEndpointClient implements HttpEndpointClient {
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final HttpClient client;
    private String accessToken;
    private String userAgent;

    public Java11HttpEndpointClient() {
        client = HttpClient.newBuilder()
                .version(HTTP_1_1)
                .followRedirects(ALWAYS)
                .build();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public <T> ListResponse<T> getList(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new ListRequest(client, userAgent, accessToken)
                .requestList(path, options, "GET", typeParam);
    }

    @Override
    public <T> PaginatedResponse<T> getPage(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new PageRequest(client, userAgent, accessToken)
                .requestPage(path, options, "GET", typeParam);
    }

    @Override
    public <T> SimpleResponse<T> getSimple(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest(client, userAgent, accessToken)
                .requestSimple(path, options, "GET", typeParam, emptyMap());
    }

    @Override
    public <T> SimpleResponse<T> postSimple(String path, Object attributes, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest(client, userAgent, accessToken)
                .requestSimple(path, options, "POST", typeParam, attributes);
    }

    @Override
    public <T> T postUnwrapped(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return new UnwrappedRequest(client, userAgent, accessToken)
                .requestUnwrappedWithBody(path, attributes, options, c, "POST");
    }

    @Override
    public EmptyResponse postEmpty(String path, Object attributes, Map<String, Object> options) throws IOException, InterruptedException, DnsimpleException {
        return new EmptyRequest(client, userAgent, accessToken)
                .requestEmpty(path, options, "POST", attributes);
    }

    @Override
    public <T> ListResponse<T> putList(String path, Object attributes, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new ListRequest(client, userAgent, accessToken)
                .requestListWithBody(path, attributes, options, typeParam, "PUT");
    }

    @Override
    public <T> SimpleResponse<T> putSimple(String path, Object attributes, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest(client, userAgent, accessToken)
                .requestSimpleWithBody(path, attributes, options, typeParam, "PUT");
    }

    @Override
    public EmptyResponse putEmpty(String path, Object attributes, Map<String, Object> options) throws IOException, InterruptedException, DnsimpleException {
        return new EmptyRequest(client, userAgent, accessToken)
                .requestEmptyWithBody(path, attributes, options, "PUT");
    }

    @Override
    public <T> SimpleResponse<T> patchSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest(client, userAgent, accessToken)
                .requestSimpleWithBody(path, attributes, options, c, "PATCH");
    }

    @Override
    public <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> options, Class<T> c) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest(client, userAgent, accessToken)
                .requestSimple(path, options, "DELETE", c, emptyMap());
    }

    @Override
    public EmptyResponse deleteEmpty(String path, Map<String, Object> options) throws IOException, InterruptedException, DnsimpleException {
        return new EmptyRequest(client, userAgent, accessToken)
                .requestEmpty(path, options, "DELETE", emptyMap());
    }
}

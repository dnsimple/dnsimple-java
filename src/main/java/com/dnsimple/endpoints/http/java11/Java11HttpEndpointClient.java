package com.dnsimple.endpoints.http.java11;

import com.dnsimple.endpoints.http.HttpEndpointClient;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;

import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.util.Collections.emptyMap;

public class Java11HttpEndpointClient implements HttpEndpointClient {
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
    public <T> ListResponse<T> getList(String path, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new ListRequest<T>(client, userAgent, accessToken)
                .execute(path, emptyMap(), queryStringParams, typeParam, "GET");
    }

    @Override
    public <T> PaginatedResponse<T> getPage(String path, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new PageRequest<T>(client, userAgent, accessToken)
                .execute(path, emptyMap(), queryStringParams, typeParam, "GET");
    }

    @Override
    public <T> SimpleResponse<T> getSimple(String path, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest<T>(client, userAgent, accessToken)
                .execute(path, emptyMap(), queryStringParams, typeParam, "GET");
    }

    @Override
    public <T> SimpleResponse<T> postSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest<T>(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, typeParam, "POST");
    }

    @Override
    public <T> T postUnwrapped(String path, Object body, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new UnwrappedRequest<T>(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, typeParam, "POST")
                .getData();
    }

    @Override
    public EmptyResponse postEmpty(String path, Object body, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return new EmptyRequest(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, null, "POST");
    }

    @Override
    public <T> ListResponse<T> putList(String path, Object body, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new ListRequest<T>(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, typeParam, "PUT");
    }

    @Override
    public <T> SimpleResponse<T> putSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest<T>(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, typeParam, "PUT");
    }

    @Override
    public EmptyResponse putEmpty(String path, Object body, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return new EmptyRequest(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, null, "PUT");
    }

    @Override
    public <T> SimpleResponse<T> patchSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest<T>(client, userAgent, accessToken)
                .execute(path, body, queryStringParams, typeParam, "PATCH");
    }

    @Override
    public <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> queryStringParams, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException {
        return new SimpleRequest<T>(client, userAgent, accessToken)
                .execute(path, emptyMap(), queryStringParams, typeParam, "DELETE");
    }

    @Override
    public EmptyResponse deleteEmpty(String path, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return new EmptyRequest(client, userAgent, accessToken)
                .execute(path, emptyMap(), queryStringParams, null, "DELETE");
    }
}

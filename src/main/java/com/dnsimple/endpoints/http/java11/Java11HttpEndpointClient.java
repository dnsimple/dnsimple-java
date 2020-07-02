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

import static com.dnsimple.endpoints.http.java11.HttpMethod.*;
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
    public <T> ListResponse<T> getList(String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).list(path, emptyMap(), queryStringParams, GET, dataType);
    }

    @Override
    public <T> PaginatedResponse<T> getPage(String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).page(path, emptyMap(), queryStringParams, GET, dataType);
    }

    @Override
    public <T> SimpleResponse<T> getSimple(String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).simple(path, emptyMap(), queryStringParams, GET, dataType);
    }

    @Override
    public <T> SimpleResponse<T> postSimple(String path, Map<String, Object> body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).simple(path, body, queryStringParams, POST, dataType);
    }

    @Override
    public <T> T postUnwrapped(String path, Map<String, Object> body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).raw(path, body, queryStringParams, POST, dataType);
    }

    @Override
    public EmptyResponse postEmpty(String path, Map<String, Object> body, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).empty(path, body, queryStringParams, POST);
    }

    @Override
    public <T> ListResponse<T> putList(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).list(path, body, queryStringParams, PUT, dataType);
    }

    @Override
    public <T> SimpleResponse<T> putSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).simple(path, body, queryStringParams, PUT, dataType);
    }

    @Override
    public EmptyResponse putEmpty(String path, Object body, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).empty(path, body, queryStringParams, PUT);
    }

    @Override
    public <T> SimpleResponse<T> patchSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).simple(path, body, queryStringParams, PATCH, dataType);
    }

    @Override
    public <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).simple(path, emptyMap(), queryStringParams, DELETE, dataType);
    }

    @Override
    public EmptyResponse deleteEmpty(String path, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).empty(path, emptyMap(), queryStringParams, DELETE);
    }
}

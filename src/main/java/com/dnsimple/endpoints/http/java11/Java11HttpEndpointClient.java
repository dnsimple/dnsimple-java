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
    public <T> ListResponse<T> list(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).list(method, path, body, queryStringParams, dataType);
    }

    @Override
    public <T> PaginatedResponse<T> page(HttpMethod method, String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).page(path, emptyMap(), queryStringParams, method, dataType);
    }

    @Override
    public <T> SimpleResponse<T> simple(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).simple(path, body, queryStringParams, method, dataType);
    }

    @Override
    public <T> SimpleResponse<T> postSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return simple(POST, path, queryStringParams, body, dataType);
    }

    @Override
    public <T> T raw(HttpMethod method, String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).raw(path, body, queryStringParams, method, dataType);
    }

    @Override
    public EmptyResponse empty(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body) throws IOException, InterruptedException, DnsimpleException {
        return new Request(client, userAgent, accessToken).empty(path, body, queryStringParams, method);
    }

    @Override
    public <T> ListResponse<T> putList(String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return list(PUT, path, queryStringParams, body, dataType);
    }

    @Override
    public <T> SimpleResponse<T> putSimple(String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return simple(PUT, path, queryStringParams, body, dataType);
    }

    @Override
    public EmptyResponse putEmpty(String path, Map<String, Object> queryStringParams, Object body) throws IOException, InterruptedException, DnsimpleException {
        return empty(PUT, path, queryStringParams, body);
    }

    @Override
    public <T> SimpleResponse<T> patchSimple(String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return simple(PATCH, path, queryStringParams, body, dataType);
    }

    @Override
    public <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException {
        return simple(DELETE, path, queryStringParams, emptyMap(), dataType);
    }

    @Override
    public EmptyResponse deleteEmpty(String path, Map<String, Object> queryStringParams) throws IOException, InterruptedException, DnsimpleException {
        return empty(DELETE, path, emptyMap(), queryStringParams);
    }
}

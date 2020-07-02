package com.dnsimple.endpoints.http;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public class HttpEndpointClient {
    private final HttpRequestFactory requestFactory;
    private String userAgent;
    private String accessToken;

    public HttpEndpointClient(HttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    EmptyResponse empty(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body) throws IOException, InterruptedException, DnsimpleException {
        return requestFactory.execute(userAgent, accessToken, path, body, queryStringParams, Void.class, EmptyResponse.class, EmptyResponse::new, method);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> SimpleResponse<DATA_TYPE> simple(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return requestFactory.execute(userAgent, accessToken, path, body, queryStringParams, dataType, SimpleResponse.class, SimpleResponse::empty, method);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> ListResponse<DATA_TYPE> list(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return requestFactory.execute(userAgent, accessToken, path, body, queryStringParams, dataType, ListResponse.class, ListResponse::empty, method);
    }

    @SuppressWarnings("unchecked")
    <DATA_TYPE> PaginatedResponse<DATA_TYPE> page(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return requestFactory.execute(userAgent, accessToken, path, body, queryStringParams, dataType, PaginatedResponse.class, PaginatedResponse::empty, method);
    }

    <DATA_TYPE> DATA_TYPE raw(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return requestFactory.execute(userAgent, accessToken, path, body, queryStringParams, method, dataType);
    }
}

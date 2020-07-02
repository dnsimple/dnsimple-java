package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.PaginatedResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;

public class PageRequest<T> implements Request<PaginatedResponse<T>, T> {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public PageRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaginatedResponse<T> execute(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType, String method) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, PaginatedResponse.class, PaginatedResponse::empty, method);
    }
}

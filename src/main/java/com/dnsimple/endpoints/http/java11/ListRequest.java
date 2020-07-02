package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;

public class ListRequest<T> implements Request<ListResponse<T>, T> {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public ListRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListResponse<T> execute(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType, String method) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, ListResponse.class, ListResponse::empty, method);
    }
}

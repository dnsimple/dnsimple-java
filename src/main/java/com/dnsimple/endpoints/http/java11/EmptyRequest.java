package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;

public class EmptyRequest implements Request<EmptyResponse, Void> {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public EmptyRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    @Override
    public EmptyResponse execute(String path, Object body, Map<String, Object> queryStringParams, Class<Void> dataType, String method) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, EmptyResponse.class, EmptyResponse::new, method);
    }
}

package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.*;

public class SimpleRequest<T> implements Request<SimpleResponse<T>, T> {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public SimpleRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SimpleResponse<T> execute(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType, String method) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, SimpleResponse.class, SimpleResponse::empty, method);

    }
}

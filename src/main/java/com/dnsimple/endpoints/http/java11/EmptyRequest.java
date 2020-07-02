package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static com.dnsimple.endpoints.http.java11.CommonRequest.buildRequest;
import static com.dnsimple.endpoints.http.java11.CommonRequest.checkStatusCode;

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
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        checkStatusCode(response);
        EmptyResponse apiResponse = new EmptyResponse();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }
}

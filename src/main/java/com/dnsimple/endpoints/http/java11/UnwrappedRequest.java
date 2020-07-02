package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.*;

/**
 * Request class to be used when the API's response doesn't wrap it's payload in
 * a <code>{"data": ...}</code> JSON object and, instead, we need to assume the whole
 * JSON object in the response is what we should provide inside a SimpleResponse
 */
public class UnwrappedRequest<T> implements Request<SimpleResponse<T>, T> {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public UnwrappedRequest(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    @Override
    public SimpleResponse<T> execute(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<T>> response = client.send(request, new JsonResponseHandler<>(dataType));
        checkStatusCode(response);
        SimpleResponse<T> apiResponse = new SimpleResponse<>(response.body().get());
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }
}

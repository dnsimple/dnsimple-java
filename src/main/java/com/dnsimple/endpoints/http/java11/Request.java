package com.dnsimple.endpoints.http.java11;

import com.dnsimple.endpoints.http.java11.CommonRequest.JsonContainerResponseHandler;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.buildRequest;
import static com.dnsimple.endpoints.http.java11.CommonRequest.checkStatusCode;

class Request {
    private final HttpClient client;
    private final String userAgent;
    private final String accessToken;

    public Request(HttpClient client, String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
        this.client = client;
    }

    public EmptyResponse empty(String path, Object body, Map<String, Object> queryStringParams, String method) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, Void.class, EmptyResponse.class, EmptyResponse::new, method);
    }

    @SuppressWarnings("unchecked")
    public <DATA_TYPE> SimpleResponse<DATA_TYPE> simple(String path, Object body, Map<String, Object> queryStringParams, String method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, SimpleResponse.class, SimpleResponse::empty, method);
    }

    @SuppressWarnings("unchecked")
    public <DATA_TYPE> ListResponse<DATA_TYPE> list(String path, Object body, Map<String, Object> queryStringParams, String method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, ListResponse.class, ListResponse::empty, method);
    }

    @SuppressWarnings("unchecked")
    public <DATA_TYPE> PaginatedResponse<DATA_TYPE> page(String path, Object body, Map<String, Object> queryStringParams, String method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        return execute(client, userAgent, accessToken, path, body, queryStringParams, dataType, PaginatedResponse.class, PaginatedResponse::empty, method);
    }

    public <DATA_TYPE> DATA_TYPE raw(String path, Object body, Map<String, Object> queryStringParams, String method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<DATA_TYPE>> response = client.send(request, new CommonRequest.JsonResponseHandler<>(dataType));
        checkStatusCode(response);
        return response.body().get();
    }

    // We need to define a TYPED_CONTAINER instead of at interface level because otherwise, we won't be able to provide a valid containerType argument
    private <DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> CONTAINER execute(HttpClient client, String userAgent, String accessToken, String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<CONTAINER>> response = client.send(request, new JsonContainerResponseHandler<>(dataType, containerType, emptyContainerSupplier));
        checkStatusCode(response);
        CONTAINER apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }
}

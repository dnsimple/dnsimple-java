package com.dnsimple.endpoints.http.java11;

import com.dnsimple.endpoints.http.java11.CommonRequest.JsonContainerResponseHandler;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ApiResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Supplier;

import static com.dnsimple.endpoints.http.java11.CommonRequest.buildRequest;
import static com.dnsimple.endpoints.http.java11.CommonRequest.checkStatusCode;

interface Request<CONTAINER, DATA_TYPE> {
    CONTAINER execute(String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType, String method) throws IOException, InterruptedException, DnsimpleException;

    // We need to define a TYPED_CONTAINER instead of at interface level because otherwise, we won't be able to provide a valid containerType argument
    default <TYPED_CONTAINER extends ApiResponse<DATA_TYPE>> TYPED_CONTAINER execute(HttpClient client, String userAgent, String accessToken, String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType, Class<TYPED_CONTAINER> containerType, Supplier<TYPED_CONTAINER> emptyContainerSupplier, String method) throws IOException, InterruptedException, DnsimpleException {
        HttpRequest request = buildRequest(path, queryStringParams, body, method, userAgent, accessToken);
        HttpResponse<Supplier<TYPED_CONTAINER>> response = client.send(request, new JsonContainerResponseHandler<>(dataType, containerType, emptyContainerSupplier));
        checkStatusCode(response);
        TYPED_CONTAINER apiResponse = response.body().get();
        apiResponse.setHttpRequest(request);
        apiResponse.setHttpResponse(response);
        return apiResponse;
    }
}

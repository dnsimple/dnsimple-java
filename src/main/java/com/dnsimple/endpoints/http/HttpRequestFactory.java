package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ApiResponse;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

public interface HttpRequestFactory {
    String API_VERSION_PATH = "/v2/";
    String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;

    <DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> CONTAINER execute(String userAgent, String accessToken, String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier, HttpMethod method) throws IOException, InterruptedException, DnsimpleException;

    <DATA_TYPE> DATA_TYPE execute(String userAgent, String accessToken, String path, Object body, Map<String, Object> queryStringParams, HttpMethod method, Class<DATA_TYPE> dataType) throws IOException, InterruptedException, DnsimpleException;
}

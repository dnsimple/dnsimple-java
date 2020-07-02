package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;
import com.dnsimple.response.ApiResponse;

import java.util.Map;
import java.util.function.Supplier;

/**
 * This interface defined the low level HTTP operations required by this library
 *
 * @see com.dnsimple.endpoints.http.java11.Java11HttpRequestFactory
 */
public interface HttpRequestFactory {
    String API_VERSION_PATH = "/v2/";
    String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;

    /**
     * Execute an HTTP request to an API endpoint that wraps results inside a <code>{"data":...}</code> JSON object
     *
     * @param userAgent              the user agent to be used on the HTTP request
     * @param accessToken            the access token to be used for authentication against the API
     * @param method                 the HTTP method to be used on the HTTP request
     * @param path                   the API endpoint's path
     * @param queryStringParams      a map of query string params to be used on the HTTP request
     * @param body                   the HTTP request's body payload
     * @param dataType               type of the output data class
     * @param containerType          type of the output container
     * @param emptyContainerSupplier a supplier that must produce an empty container for HTTP 204 responses
     * @return a container object containing objects of the provided data type
     */
    <DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> CONTAINER execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier);

    /**
     * Execute an HTTP request to an API endpoint
     *
     * @param userAgent         the user agent to be used on the HTTP request
     * @param accessToken       the access token to be used for authentication against the API
     * @param method            the HTTP method to be used on the HTTP request
     * @param path              the API endpoint's path
     * @param queryStringParams a map of query string params to be used on the HTTP request
     * @param body              the HTTP request's body payload
     * @param dataType          type of the output data class
     * @return an object of the provided data type
     */
    <DATA_TYPE> DATA_TYPE execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<DATA_TYPE> dataType);
}

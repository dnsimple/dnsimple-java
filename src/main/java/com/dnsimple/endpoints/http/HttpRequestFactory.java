package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;

import java.util.Map;

/**
 * This interface defined the low level HTTP operations required by this library
 *
 * @see com.dnsimple.endpoints.http.java11.Java11HttpRequestFactory
 */
public interface HttpRequestFactory {
    String API_VERSION_PATH = "/v2/";
    String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;

    /**
     * Execute an HTTP request to an API endpoint
     *
     * @param userAgent         the user agent to be used on the HTTP request
     * @param accessToken       the access token to be used for authentication against the API
     * @param method            the HTTP method to be used on the HTTP request
     * @param path              the API endpoint's path
     * @param queryStringParams a map of query string params to be used on the HTTP request
     * @param body              the HTTP request's body payload
     * @return an RawResponse object with the status code, headers and body of the HTTP response
     */
    RawResponse execute(String userAgent, String accessToken, HttpMethod method, String path, Map<String, Object> queryStringParams, Object body);
}

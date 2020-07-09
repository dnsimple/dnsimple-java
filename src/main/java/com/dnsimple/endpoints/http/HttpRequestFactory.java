package com.dnsimple.endpoints.http;

import java.net.URI;
import java.util.Optional;

/**
 * This interface defined the low level HTTP operations required by this library
 *
 * @see com.dnsimple.endpoints.http.java11.Java11HttpRequestFactory
 */
public interface HttpRequestFactory {
    /**
     * Execute an HTTP request to an API endpoint
     *
     * @param userAgent         the user agent to be used on the HTTP request
     * @param accessToken       the access token to be used for authentication against the API
     * @param method            the HTTP method to be used on the HTTP request
     * @param uri               the URI to be requested
     * @param body              the HTTP request's body payload
     * @return an RawResponse object with the status code, headers and body of the HTTP response
     */
    RawResponse execute(String userAgent, Optional<String> accessToken, HttpMethod method, URI uri, Object body);
}

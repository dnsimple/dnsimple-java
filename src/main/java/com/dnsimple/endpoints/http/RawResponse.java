package com.dnsimple.endpoints.http;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * RawResponse holds key information about an HTTP response from DNSimple's API, including
 * the status code, headers, and the response's body in the form of an InputStream.
 */
public class RawResponse {
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final InputStream body;

    public RawResponse(int statusCode, Map<String, List<String>> headers, InputStream body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public InputStream getBody() {
        return body;
    }
}

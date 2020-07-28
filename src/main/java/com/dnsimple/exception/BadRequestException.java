package com.dnsimple.exception;

import java.util.Map;

public class BadRequestException extends DnsimpleException {
    private final int statusCode;
    private final Map<String, Object> body;

    public BadRequestException(int statusCode, Map<String, Object> body) {
        super("The server responded with an HTTP " + statusCode + " error. Please, review the request options and attributes");
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}

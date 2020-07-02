package com.dnsimple.exception;

public class BadRequestException extends DnsimpleException {
    private final int statusCode;

    public BadRequestException(int statusCode) {
        super("The server responded with an HTTP " + statusCode + " error. Please, review the request options and attributes");
        this.statusCode = statusCode;
    }

    public BadRequestException(int statusCode, Throwable cause) {
        super("The server responded with an HTTP " + statusCode + " error. Please, review the request options and attributes", cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

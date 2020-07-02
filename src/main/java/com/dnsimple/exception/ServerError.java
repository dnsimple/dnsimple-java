package com.dnsimple.exception;

public class ServerError extends DnsimpleException {
    private final int statusCode;

    public ServerError(int statusCode) {
        super("The server failed to process the request and responsed with an HTTP " + statusCode + " error");
        this.statusCode = statusCode;
    }

    public ServerError(int statusCode, Throwable cause) {
        super("The server failed to process the request and responsed with an HTTP " + statusCode + " error", cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

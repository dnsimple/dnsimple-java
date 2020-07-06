package com.dnsimple.exception;

public class ServerError extends DnsimpleException {
    private final int statusCode;

    public ServerError(int statusCode) {
        super("The server failed to process the request and responded with an HTTP " + statusCode + " error");
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

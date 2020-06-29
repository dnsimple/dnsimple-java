package com.dnsimple.exception;

public class DnsimpleException extends Exception {
    private String requestId;
    private Integer statusCode;

    public DnsimpleException(String message, String requestId, Integer statusCode) {
        super(message, null);
        this.requestId = requestId;
        this.statusCode = statusCode;
    }

    public DnsimpleException(String message, String requestId, Integer statusCode, Throwable e) {
        super(message, e);
        this.requestId = requestId;
        this.statusCode = statusCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public static final long serialVersionUID = 1L;
}

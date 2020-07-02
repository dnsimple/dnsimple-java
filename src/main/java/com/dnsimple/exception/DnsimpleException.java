package com.dnsimple.exception;

public class DnsimpleException extends RuntimeException {
    public DnsimpleException() {
    }

    public DnsimpleException(String message) {
        super(message);
    }

    public DnsimpleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DnsimpleException(Throwable cause) {
        super(cause);
    }

    public DnsimpleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

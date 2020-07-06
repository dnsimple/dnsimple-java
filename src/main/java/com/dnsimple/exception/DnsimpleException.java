package com.dnsimple.exception;

public class DnsimpleException extends RuntimeException {
    public DnsimpleException() {
    }

    public DnsimpleException(String message) {
        super(message);
    }

    public DnsimpleException(Throwable cause) {
        super(cause);
    }
}

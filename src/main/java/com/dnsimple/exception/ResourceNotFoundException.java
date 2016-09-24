package com.dnsimple.exception;

public class ResourceNotFoundException extends DnsimpleException {
  public ResourceNotFoundException(String message, String requestId, Integer statusCode) {
    super(message, requestId, statusCode);
  }

  public ResourceNotFoundException(String message, String requestId, Integer statusCode, Throwable e) {
    super(message, requestId, statusCode, e);
  }

  public static final long serialVersionUID = 1L;
}

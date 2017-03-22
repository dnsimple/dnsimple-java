package com.dnsimple.exception;

import com.google.api.client.http.HttpResponseException;

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

  /**
   * Transform an HttpResponseException into a DnsimpleException.
   *
   * @param e The HttpResponseException
   * @return The DnsimpleException
   */
  public static DnsimpleException transformException(HttpResponseException e) {
    switch(e.getStatusCode()) {
      case 404:
        return new ResourceNotFoundException("Failed to retreive domain: " + e.getMessage(), null, e.getStatusCode(), e);
      default:
        return new DnsimpleException(e.getMessage(), null, e.getStatusCode(), e);
    }
  }

  public static final long serialVersionUID = 1L;
}

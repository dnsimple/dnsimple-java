package com.dnsimple.response;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;

public abstract class ApiResponse {
  private HttpRequest httpRequest;
  private HttpResponse httpResponse;

  public HttpRequest getHttpRequest() {
    return httpRequest;
  }

  public void setHttpRequest(HttpRequest httpRequest) {
    this.httpRequest = httpRequest;
  }

  public HttpResponse getHttpResponse() {
    return httpResponse;
  }

  public void setHttpResponse(HttpResponse httpResponse) {
    this.httpResponse = httpResponse;
  }
}

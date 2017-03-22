package com.dnsimple.response;

import com.dnsimple.data.Certificate;

import com.google.api.client.util.Key;

public class GetCertificateResponse extends ApiResponse {
  @Key("data")
  private Certificate data;

  public GetCertificateResponse() {
    this(new Certificate());
  }

  public GetCertificateResponse(Certificate data) {
    this.data = data;
  }

  public Certificate getData() {
    return data;
  }
}

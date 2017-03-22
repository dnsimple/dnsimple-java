package com.dnsimple.response;

import com.dnsimple.data.CertificateBundle;

import com.google.api.client.util.Key;

public class GetCertificatePrivateKeyResponse extends ApiResponse {
  @Key("data")
  private CertificateBundle data;

  public GetCertificatePrivateKeyResponse() {
    this(new CertificateBundle());
  }

  public GetCertificatePrivateKeyResponse(CertificateBundle data) {
    this.data = data;
  }

  public CertificateBundle getData() {
    return data;
  }
}

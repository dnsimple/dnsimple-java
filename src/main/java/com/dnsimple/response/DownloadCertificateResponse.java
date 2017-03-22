package com.dnsimple.response;

import com.dnsimple.data.CertificateBundle;

import com.google.api.client.util.Key;

public class DownloadCertificateResponse extends ApiResponse {
  @Key("data")
  private CertificateBundle data;

  public DownloadCertificateResponse() {
    this(new CertificateBundle());
  }

  public DownloadCertificateResponse(CertificateBundle data) {
    this.data = data;
  }

  public CertificateBundle getData() {
    return data;
  }
}

package com.dnsimple.response;

import com.dnsimple.CertificateBundle;

import com.google.api.client.util.Key;

public class DownloadCertificateResponse extends ApiResponse {
  @Key("data")
  private CertificateBundle data;

  public CertificateBundle getData() {
    return data;
  }
}

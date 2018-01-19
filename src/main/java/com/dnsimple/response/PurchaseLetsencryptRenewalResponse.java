package com.dnsimple.response;

import com.dnsimple.data.CertificateRenewal;
import com.google.api.client.util.Key;

public class PurchaseLetsencryptRenewalResponse extends ApiResponse {
  @Key("data")
  private CertificateRenewal data;

  public PurchaseLetsencryptRenewalResponse() {
    this(new CertificateRenewal());
  }

  public PurchaseLetsencryptRenewalResponse(CertificateRenewal data) {
    this.data = data;
  }

  public CertificateRenewal getData() {
    return data;
  }
}

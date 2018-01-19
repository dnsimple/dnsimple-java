package com.dnsimple.response;

import com.dnsimple.data.CertificatePurchase;

import com.google.api.client.util.Key;

public class PurchaseLetsencryptResponse extends ApiResponse {
  @Key("data")
  private CertificatePurchase data;

  public PurchaseLetsencryptResponse() {
    this(new CertificatePurchase());
  }

  public PurchaseLetsencryptResponse(CertificatePurchase data) {
    this.data = data;
  }

  public CertificatePurchase getData() {
    return data;
  }
}

package com.dnsimple.response;

import com.dnsimple.data.Certificate;

import com.google.api.client.util.Key;

public class PurchaseLetsencryptResponse extends ApiResponse {
  @Key("data")
  private Certificate data;

  public PurchaseLetsencryptResponse() {
    this(new Certificate());
  }

  public PurchaseLetsencryptResponse(Certificate data) {
    this.data = data;
  }

  public Certificate getData() {
    return data;
  }
}

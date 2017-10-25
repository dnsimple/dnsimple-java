package com.dnsimple.response;

import com.dnsimple.data.Certificate;

import com.google.api.client.util.Key;

public class LetsencryptPurchaseResponse extends ApiResponse {
  @Key("data")
  private Certificate data;

  public LetsencryptPurchaseResponse() {
    this(new Certificate());
  }

  public LetsencryptPurchaseResponse(Certificate data) {
    this.data = data;
  }
}

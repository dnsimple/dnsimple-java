package com.dnsimple.response;

import com.dnsimple.data.Certificate;
import com.google.api.client.util.Key;

public class IssueLetsencryptRenewalResponse extends ApiResponse {
  @Key("data")
  private Certificate data;

  public IssueLetsencryptRenewalResponse() {
    this(new Certificate());
  }

  public IssueLetsencryptRenewalResponse(Certificate data) {
    this.data = data;
  }

  public Certificate getData() {
    return data;
  }
}

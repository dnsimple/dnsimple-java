package com.dnsimple.response;

import com.dnsimple.data.Certificate;
import com.google.api.client.util.Key;

public class IssueLetsencryptResponse extends ApiResponse {
  @Key("data")
  private Certificate data;

  public IssueLetsencryptResponse() {
    this(new Certificate());
  }

  public IssueLetsencryptResponse(Certificate data) {
    this.data = data;
  }

  public Certificate getData() {
    return data;
  }
}

package com.dnsimple.response;

import com.dnsimple.data.Certificate;

import com.google.api.client.util.Key;

public class GetCertificateResponse extends ApiResponse {
  @Key("data")
  private Certificate data;

  public Certificate getData() {
    return data;
  }
}

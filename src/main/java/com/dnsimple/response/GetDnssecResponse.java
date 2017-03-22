package com.dnsimple.response;

import com.dnsimple.data.Dnssec;

import com.google.api.client.util.Key;

public class GetDnssecResponse extends ApiResponse {
  @Key("data")
  private Dnssec data;

  public Dnssec getData() {
    return data;
  }
}

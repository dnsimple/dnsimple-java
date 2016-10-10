package com.dnsimple.response;

import com.dnsimple.data.Tld;

import com.google.api.client.util.Key;

public class GetTldResponse extends ApiResponse {
  @Key("data")
  private Tld data;

  public Tld getData() {
    return data;
  }
}

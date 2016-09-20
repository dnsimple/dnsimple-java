package com.dnsimple;

import com.google.api.client.util.Key;

public class DnsimpleResponse {
  @Key("data")
  private WhoamiResponse data;

  public DnsimpleResponse() {}
  public DnsimpleResponse(WhoamiResponse data) {
    this.data = data;
  }

  public WhoamiResponse getData() {
    return data;
  }
}

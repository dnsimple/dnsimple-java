package com.dnsimple.response;

import com.dnsimple.data.DomainRenewal;

import com.google.api.client.util.Key;

public class RenewDomainResponse extends ApiResponse {
  @Key("data")
  private DomainRenewal data;

  public DomainRenewal getData() {
    return data;
  }
}

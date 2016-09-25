package com.dnsimple.response;

import com.dnsimple.DomainAvailability;

import com.google.api.client.util.Key;

public class CheckDomainResponse extends ApiResponse {
  @Key("data")
  private DomainAvailability data;

  public DomainAvailability getData() {
    return data;
  }
}

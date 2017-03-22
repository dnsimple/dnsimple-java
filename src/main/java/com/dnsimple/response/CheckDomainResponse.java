package com.dnsimple.response;

import com.dnsimple.data.DomainAvailability;

import com.google.api.client.util.Key;

public class CheckDomainResponse extends ApiResponse {
  @Key("data")
  private DomainAvailability data;

  public CheckDomainResponse() {
    this(new DomainAvailability());
  }

  public CheckDomainResponse(DomainAvailability data) {
    this.data = data;
  }

  public DomainAvailability getData() {
    return data;
  }
}

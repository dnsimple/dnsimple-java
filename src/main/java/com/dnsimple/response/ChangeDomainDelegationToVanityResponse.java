package com.dnsimple.response;

import java.util.List;

import com.dnsimple.NameServer;

import com.google.api.client.util.Key;

public class ChangeDomainDelegationToVanityResponse extends ApiResponse {
  @Key("data")
  private List<NameServer> data;

  public List<NameServer> getData() {
    return data;
  }
}

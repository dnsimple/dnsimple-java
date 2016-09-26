package com.dnsimple.response;

import java.util.List;

import com.google.api.client.util.Key;

public class ChangeDomainDelegationResponse extends ApiResponse {
  @Key("data")
  private List<String> data;

  public List<String> getData() {
    return data;
  }
}

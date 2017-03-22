package com.dnsimple.response;

import com.google.api.client.util.Key;

import java.util.List;
import java.util.ArrayList;

public class ChangeDomainDelegationResponse extends ApiResponse {
  @Key("data")
  private List<String> data;

  public ChangeDomainDelegationResponse() {
    this(new ArrayList<String>());
  }

  public ChangeDomainDelegationResponse(List<String> data) {
    this.data = data;
  }

  public List<String> getData() {
    return data;
  }
}

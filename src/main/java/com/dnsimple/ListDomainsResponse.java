package com.dnsimple;

import java.util.List;

import com.google.api.client.util.Key;

public class ListDomainsResponse {
  @Key("data")
  private List<Domain> data;

  public List<Domain> getData() {
    return data;
  }
}

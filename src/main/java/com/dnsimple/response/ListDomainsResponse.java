package com.dnsimple.response;

import com.dnsimple.Domain;

import java.util.List;

import com.google.api.client.util.Key;

public class ListDomainsResponse implements ApiResponse {
  @Key("data")
  private List<Domain> data;

  public List<Domain> getData() {
    return data;
  }
}

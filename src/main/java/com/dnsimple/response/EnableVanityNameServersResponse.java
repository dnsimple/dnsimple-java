package com.dnsimple.response;

import com.dnsimple.data.NameServer;

import java.util.List;

import com.google.api.client.util.Key;

public class EnableVanityNameServersResponse extends ApiResponse {
  @Key("data")
  private List<NameServer> data;

  public List<NameServer> getData() {
    return data;
  }
}

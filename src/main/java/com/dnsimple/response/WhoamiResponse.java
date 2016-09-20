package com.dnsimple.response;

import com.dnsimple.Whoami;

import com.google.api.client.util.Key;

public class WhoamiResponse implements ApiResponse {
  @Key("data")
  private Whoami data;

  public WhoamiResponse() {}
  public WhoamiResponse(Whoami data) {
    this.data = data;
  }

  public Whoami getData() {
    return data;
  }
}

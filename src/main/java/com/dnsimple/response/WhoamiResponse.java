package com.dnsimple.response;

import com.dnsimple.data.Whoami;

import com.google.api.client.util.Key;

public class WhoamiResponse extends ApiResponse {
  @Key("data")
  private Whoami data;

  public WhoamiResponse() {
    this(new Whoami());
  }

  public WhoamiResponse(Whoami data) {
    this.data = data;
  }

  public Whoami getData() {
    return data;
  }
}

package com.dnsimple;

import com.google.api.client.util.Key;

public class WhoamiResponse {
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

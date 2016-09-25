package com.dnsimple.response;

import com.google.api.client.util.Key;

public class AcceptPushResponse extends ApiResponse {
  @Key("data")
  private Object data;

  public Object getData() {
    return data;
  }
}

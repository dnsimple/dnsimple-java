package com.dnsimple.response;

import com.dnsimple.data.Push;

import com.google.api.client.util.Key;

public class InitiatePushResponse extends ApiResponse {
  @Key("data")
  private Push data;

  public Push getData() {
    return data;
  }
}

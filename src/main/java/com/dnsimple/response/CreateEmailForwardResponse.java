package com.dnsimple.response;

import com.dnsimple.data.EmailForward;

import com.google.api.client.util.Key;

public class CreateEmailForwardResponse extends ApiResponse {
  @Key("data")
  private EmailForward data;

  public EmailForward getData() {
    return data;
  }
}

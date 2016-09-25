package com.dnsimple.response;

import com.dnsimple.EmailForward;

import java.util.List;

import com.google.api.client.util.Key;

public class GetEmailForwardResponse extends ApiResponse {
  @Key("data")
  private EmailForward data;

  public EmailForward getData() {
    return data;
  }
}

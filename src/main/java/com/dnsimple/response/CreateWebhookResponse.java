package com.dnsimple.response;

import com.dnsimple.Webhook;

import com.google.api.client.util.Key;

public class CreateWebhookResponse extends ApiResponse {
  @Key("data")
  private Webhook data;

  public Webhook getData() {
    return data;
  }
}

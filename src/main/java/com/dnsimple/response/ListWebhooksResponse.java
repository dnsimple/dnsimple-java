package com.dnsimple.response;

import com.dnsimple.Webhook;

import java.util.List;

import com.google.api.client.util.Key;

public class ListWebhooksResponse extends ApiResponse {
  @Key("data")
  private List<Webhook> data;

  public List<Webhook> getData() {
    return data;
  }
}

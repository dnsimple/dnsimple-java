package com.dnsimple.response;

import com.dnsimple.TemplateRecord;

import com.google.api.client.util.Key;

public class CreateTemplateRecordResponse extends ApiResponse {
  @Key("data")
  private TemplateRecord data;

  public TemplateRecord getData() {
    return data;
  }
}

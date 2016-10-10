package com.dnsimple.response;

import com.dnsimple.data.Template;

import com.google.api.client.util.Key;

public class UpdateTemplateResponse extends ApiResponse {
  @Key("data")
  private Template data;

  public Template getData() {
    return data;
  }
}

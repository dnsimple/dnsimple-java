package com.dnsimple.response;

import com.dnsimple.WhoisPrivacy;

import com.google.api.client.util.Key;

public class GetWhoisPrivacyResponse extends ApiResponse {
  @Key("data")
  private WhoisPrivacy data;

  public WhoisPrivacy getData() {
    return data;
  }
}

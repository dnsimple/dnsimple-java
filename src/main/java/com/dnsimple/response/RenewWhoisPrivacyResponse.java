package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacyRenewal;

import com.google.api.client.util.Key;

public class RenewWhoisPrivacyResponse extends ApiResponse {
  @Key("data")
  private WhoisPrivacyRenewal data;

  public WhoisPrivacyRenewal getData() {
    return data;
  }
}

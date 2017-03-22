package com.dnsimple.response;

import com.dnsimple.data.DelegationSignerRecord;

import com.google.api.client.util.Key;

public class GetDelegationSignerRecordResponse extends ApiResponse {
  @Key("data")
  private DelegationSignerRecord data;

  public DelegationSignerRecord getData() {
    return data;
  }
}


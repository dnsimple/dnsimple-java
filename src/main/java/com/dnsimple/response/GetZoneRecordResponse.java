package com.dnsimple.response;

import com.dnsimple.ZoneRecord;

import com.google.api.client.util.Key;

public class GetZoneRecordResponse extends ApiResponse {
  @Key("data")
  private ZoneRecord data;

  public ZoneRecord getData() {
    return data;
  }
}

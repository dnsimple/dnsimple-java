package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;

import com.google.api.client.util.Key;

public class UpdateZoneRecordResponse extends ApiResponse {
  @Key("data")
  private ZoneRecord data;

  public ZoneRecord getData() {
    return data;
  }
}

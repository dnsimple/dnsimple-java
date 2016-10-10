package com.dnsimple.response;

import com.dnsimple.data.Zone;

import com.google.api.client.util.Key;

public class GetZoneResponse extends ApiResponse {
  @Key("data")
  private Zone data;

  public Zone getData() {
    return data;
  }
}

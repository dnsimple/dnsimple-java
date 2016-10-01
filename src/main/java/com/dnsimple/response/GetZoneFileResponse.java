package com.dnsimple.response;

import com.dnsimple.ZoneFile;

import com.google.api.client.util.Key;

public class GetZoneFileResponse extends ApiResponse {
  @Key("data")
  private ZoneFile data;

  public ZoneFile getData() {
    return data;
  }
}

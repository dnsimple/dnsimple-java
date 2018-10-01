package com.dnsimple.response;

import com.dnsimple.data.ZoneDistribution;

import com.google.api.client.util.Key;

public class CheckZoneDistributionResponse extends ApiResponse {
  @Key("data")
  private ZoneDistribution data;

  public ZoneDistribution getData() {
    return data;
  }
}

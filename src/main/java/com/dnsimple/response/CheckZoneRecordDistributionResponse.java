package com.dnsimple.response;

import com.dnsimple.data.ZoneRecordDistribution;

import com.google.api.client.util.Key;

public class CheckZoneRecordDistributionResponse extends ApiResponse {
  @Key("data")
  private ZoneRecordDistribution data;

  public ZoneRecordDistribution getData() {
    return data;
  }
}

package com.dnsimple.data;

import com.google.api.client.util.Key;

public class ZoneRecordDistribution {
  @Key("distributed")
  private boolean distributed;

  public boolean getDistributed() {
    return distributed;
  }

}

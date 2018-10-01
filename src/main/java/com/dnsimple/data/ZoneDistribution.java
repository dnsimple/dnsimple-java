package com.dnsimple.data;

import com.google.api.client.util.Key;

public class ZoneDistribution {
  @Key("distributed")
  private boolean distributed;

  public boolean isDistributed() {
    return distributed;
  }

}

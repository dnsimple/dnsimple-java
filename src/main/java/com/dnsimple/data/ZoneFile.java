package com.dnsimple.data;

import com.google.api.client.util.Key;

public class ZoneFile {
  @Key("zone")
  private String zone;

  public String getZone() {
    return zone;
  }

}

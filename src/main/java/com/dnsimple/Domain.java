package com.dnsimple;

import com.google.api.client.util.Key;

public class Domain {
  @Key("id")
  private int id;

  public int getId() {
    return id;
  }
}

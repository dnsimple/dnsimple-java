package com.dnsimple;

import com.google.api.client.util.Key;

public class Webhook {
  @Key("id")
  private Integer id;

  @Key("url")
  private String url;

  public Integer getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }
}

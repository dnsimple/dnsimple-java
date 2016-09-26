package com.dnsimple;

import com.google.api.client.util.Key;

public class NameServer {
  @Key("id")
  private Integer id;

  @Key("name")
  private String name;

  @Key("ipv4")
  private String ipv4;

  @Key("ipv6")
  private String ipv6;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getIpv4() {
    return ipv4;
  }

  public String getIpv6() {
    return ipv6;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

}

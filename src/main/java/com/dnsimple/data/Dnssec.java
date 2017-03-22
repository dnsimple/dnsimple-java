package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Dnssec {
  @Key("enabled")
  private Boolean enabled;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Boolean getEnabled() {
    return enabled;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

}

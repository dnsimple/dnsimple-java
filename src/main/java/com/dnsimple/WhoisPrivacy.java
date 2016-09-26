package com.dnsimple;

import com.google.api.client.util.Key;

public class WhoisPrivacy {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("expires_on")
  private String expiresOn;

  @Key("enabled")
  private Boolean enabled;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public Integer getDomainId() {
    return domainId;
  }

  public String getExpiresOn() {
    return expiresOn;
  }

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

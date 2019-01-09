package com.dnsimple.data;

import com.google.api.client.util.Key;

public class WhoisPrivacyRenewal {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("whois_privacy_id")
  private Integer whoisPrivacyId;

  @Key("state")
  private String state;

  @Key("enabled")
  private Boolean enabled;

  @Key("expires_on")
  private String expiresOn;

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

  public Integer getWhoisPrivacyId() {
    return whoisPrivacyId;
  }

  public String getState() {
    return state;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public String getExpiresOn() {
    return expiresOn;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}

package com.dnsimple.data;

import com.google.api.client.util.Key;

public class DomainTransfer {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("registrant_id")
  private Integer registrantId;

  @Key("state")
  private String state;

  @Key("auto_renew")
  private boolean autoRenew;

  @Key("whois_privacy")
  private boolean whoisPrivacy;

  @Key("status_description")
  private String statusDescription;

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

  public Integer getRegistrantId() {
    return registrantId;
  }

  public String getState() {
    return state;
  }

  public boolean hasAutoRenew() {
    return autoRenew;
  }

  public boolean hasWhoisPrivacy() {
    return whoisPrivacy;
  }

  public String getStatusDescription() { 
    return statusDescription; 
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}

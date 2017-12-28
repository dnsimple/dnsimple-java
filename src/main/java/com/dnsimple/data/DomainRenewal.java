package com.dnsimple.data;

import com.google.api.client.util.Key;

public class DomainRenewal {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("period")
  private Integer period;

  @Key("state")
  private String state;

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

  public Integer getPeriod() {
    return period;
  }

  public String getState() {
    return state;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}

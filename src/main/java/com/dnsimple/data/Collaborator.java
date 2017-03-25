package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Collaborator {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

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

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

}

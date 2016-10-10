package com.dnsimple.data;

import com.google.api.client.util.Key;

public class EmailForward {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("from")
  private String from;

  @Key("to")
  private String to;

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

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}

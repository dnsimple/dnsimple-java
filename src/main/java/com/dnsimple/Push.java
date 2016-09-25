package com.dnsimple;

import com.google.api.client.util.Key;

public class Push {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("contact_id")
  private Integer contactId;

  @Key("account_id")
  private Integer accountId;

  @Key("accepted_at")
  private String acceptedAt;

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

  public Integer getContactId() {
    return contactId;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public String getAcceptedAt() {
    return acceptedAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}

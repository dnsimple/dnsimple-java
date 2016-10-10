package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Certificate {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("name")
  private String name;
  
  @Key("common_name")
  private String commonName;

  @Key("years")
  private Integer years;

  @Key("csr")
  private String csr;

  @Key("state")
  private String state;

  @Key("authority_identifier")
  private String authorityIdentifier;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  @Key("expires_on")
  private String expiresOn;

  public Integer getId() {
    return id;
  }

  public Integer getDomainId() {
    return domainId;
  }

  public String getName() {
    return name;
  }

  public String getCommonName() {
    return commonName;
  }

  public Integer getYears() {
    return years;
  }

  public String getCsr() {
    return csr;
  }

  public String getState() {
    return state;
  }

  public String getAuthorityIdentifier() {
    return authorityIdentifier;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public String getExpiresOn() {
    return expiresOn;
  }
}

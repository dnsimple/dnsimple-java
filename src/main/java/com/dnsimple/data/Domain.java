package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Domain {
  @Key("id")
  private Integer id;

  @Key("account_id")
  private Integer accountId;

  @Key("registrant_id")
  private Integer registrantId;

  @Key("name")
  private String name;

  @Key("unicode_name")
  private String unicodeName;

  @Key("token")
  private String token;

  @Key("state")
  private String state;

  @Key("auto_renew")
  private boolean autoRenew;

  @Key("private_whois")
  private boolean privateWhois;

  @Key("expires_on")
  private String expiresOn;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public Integer getRegistrantId() {
    return registrantId;
  }

  public String getName() {
    return name;
  }

  public String getUnicodeName() {
    return unicodeName;
  }

  public String getToken() {
    return token;
  }

  public String getState() {
    return state;
  }

  public boolean getAutoRenew() {
    return autoRenew;
  }

  public boolean getPrivateWhois() {
    return privateWhois;
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

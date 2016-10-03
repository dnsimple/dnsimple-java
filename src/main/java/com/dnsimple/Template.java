package com.dnsimple;

import com.google.api.client.util.Key;

public class Template {
  @Key("id")
  private Integer id;

  @Key("account_id")
  private Integer accountId;

  @Key("name")
  private String name;

  @Key("short_name")
  private String shortName;

  @Key("description")
  private String description;

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

  public String getName() {
    return name;
  }

  public String getShortName() {
    return shortName;
  }

  public String getDescription() {
    return description;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }


}

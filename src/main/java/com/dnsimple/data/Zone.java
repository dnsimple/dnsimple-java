package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Zone {
  @Key("id")
  private Integer id;
  
  @Key("account_id")
  private Integer accountId;

  @Key("name")
  private String name;

  @Key("reverse")
  private Boolean reverse;

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

  public Boolean getReverse() {
    return reverse;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

}

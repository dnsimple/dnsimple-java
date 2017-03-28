package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Collaborator {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("domain_name")
  private String domainName;

  @Key("user_id")
  private Integer userId;

  @Key("user_email")
  private String userEmail;

  @Key("invitation")
  private Boolean invitation;

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

  public String getDomainName() {
    return domainName;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public Boolean getInvitation() {
    return invitation;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

}

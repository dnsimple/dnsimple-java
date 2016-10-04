package com.dnsimple;

import com.google.api.client.util.Key;

public class TemplateRecord {
  @Key("id")
  private Integer id;

  @Key("template_id")
  private Integer templateId;

  @Key("name")
  private String name;

  @Key("content")
  private String content;

  @Key("ttl")
  private Integer ttl;

  @Key("priority")
  private Integer priority;

  @Key("type")
  private String type;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public Integer getTemplateId() {
    return templateId;
  }

  public String getName() {
    return name;
  }

  public String getContent() {
    return content;
  }

  public Integer getTtl() {
    return ttl;
  }

  public Integer getPriority() {
    return priority;
  }

  public String getType() {
    return type;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}

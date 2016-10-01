package com.dnsimple;

import com.google.api.client.util.Key;

public class ZoneRecord {
  @Key("id")
  private Integer id;

  @Key("zone_id")
  private String zoneId;

  @Key("parent_id")
  private Integer parentId;

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

  @Key("system_record")
  private Boolean systemRecord;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public String getZoneId() {
    return zoneId;
  }

  public Integer getParentId() {
    return parentId;
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

  public Boolean getSystemRecord() {
    return systemRecord;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }


}

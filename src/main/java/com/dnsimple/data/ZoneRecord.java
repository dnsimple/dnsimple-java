package com.dnsimple.data;

public class ZoneRecord {

  private Integer id;


  private String zoneId;


  private Integer parentId;


  private String name;


  private String content;


  private Integer ttl;


  private Integer priority;


  private String type;


  private Boolean systemRecord;


  private String createdAt;


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

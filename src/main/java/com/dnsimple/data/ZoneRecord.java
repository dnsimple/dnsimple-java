package com.dnsimple.data;

public class ZoneRecord {
    private final Integer id;
    private final String zoneId;
    private final Integer parentId;
    private final String name;
    private final String content;
    private final Integer ttl;
    private final Integer priority;
    private final String type;
    private final Boolean systemRecord;
    private final String createdAt;
    private final String updatedAt;

    public ZoneRecord(Integer id, String zoneId, Integer parentId, String name, String content, Integer ttl, Integer priority, String type, Boolean systemRecord, String createdAt, String updatedAt) {
        this.id = id;
        this.zoneId = zoneId;
        this.parentId = parentId;
        this.name = name;
        this.content = content;
        this.ttl = ttl;
        this.priority = priority;
        this.type = type;
        this.systemRecord = systemRecord;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

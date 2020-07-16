package com.dnsimple.data;

import java.time.OffsetDateTime;
import java.util.List;

public class ZoneRecord {
    private final Long id;
    private final String zoneId;
    private final Long parentId;
    private final String type;
    private final String name;
    private final String content;
    private final Integer ttl;
    private final Integer priority;
    private final Boolean systemRecord;
    private final List<String> regions;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public ZoneRecord(Long id, String zoneId, Long parentId, String type, String name, String content, Integer ttl, Integer priority, Boolean systemRecord, List<String> regions, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.zoneId = zoneId;
        this.parentId = parentId;
        this.type = type;
        this.name = name;
        this.content = content;
        this.ttl = ttl;
        this.priority = priority;
        this.systemRecord = systemRecord;
        this.regions = regions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getType() {
        return type;
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

    public Boolean isSystemRecord() {
        return systemRecord;
    }

    public List<String> getRegions() {
        return regions;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

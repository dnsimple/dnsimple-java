package com.dnsimple.data;

import java.time.OffsetDateTime;

public class TemplateRecord {
    private final Long id;
    private final Long templateId;
    private final String name;
    private final String content;
    private final Integer ttl;
    private final String type;
    private final Integer priority;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public TemplateRecord(Long id, Long templateId, String name, String content, Integer ttl, String type, Integer priority, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.templateId = templateId;
        this.name = name;
        this.content = content;
        this.ttl = ttl;
        this.type = type;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getTemplateId() {
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

    public String getType() {
        return type;
    }

    public Integer getPriority() {
        return priority;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

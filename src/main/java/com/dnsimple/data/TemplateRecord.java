package com.dnsimple.data;

public class TemplateRecord {
    private final Integer id;
    private final Integer templateId;
    private final String name;
    private final String content;
    private final Integer ttl;
    private final Integer priority;
    private final String type;
    private final String createdAt;
    private final String updatedAt;

    public TemplateRecord(Integer id, Integer templateId, String name, String content, Integer ttl, Integer priority, String type, String createdAt, String updatedAt) {
        this.id = id;
        this.templateId = templateId;
        this.name = name;
        this.content = content;
        this.ttl = ttl;
        this.priority = priority;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

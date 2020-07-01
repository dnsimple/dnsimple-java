package com.dnsimple.data;

public class TemplateRecord {
    private Integer id;
    private Integer templateId;
    private String name;
    private String content;
    private Integer ttl;
    private Integer priority;
    private String type;
    private String createdAt;
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

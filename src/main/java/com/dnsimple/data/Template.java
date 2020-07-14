package com.dnsimple.data;

import java.time.OffsetDateTime;

public class Template {
    private final Long id;
    private final String sid;
    private final Long accountId;
    private final String name;
    private final String description;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public Template(Long id, String sid, Long accountId, String name, String description, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.sid = sid;
        this.accountId = accountId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getSid() {
        return sid;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

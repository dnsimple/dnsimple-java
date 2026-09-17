package com.dnsimple.data;

import java.time.OffsetDateTime;

public class DomainRestore {
    private final Long id;
    private final Long domainId;
    private final String state;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public DomainRestore(Long id, Long domainId, String state, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getState() {
        return state;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

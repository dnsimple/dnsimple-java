package com.dnsimple.data;

import java.time.OffsetDateTime;

public class EmailForward {
    private final Long id;
    private final Long domainId;
    private final String aliasEmail;
    private final String destinationEmail;
    private final Boolean active;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public EmailForward(Long id, Long domainId, String aliasEmail, String destinationEmail, Boolean active, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.aliasEmail = aliasEmail;
        this.destinationEmail = destinationEmail;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getAliasEmail() {
        return aliasEmail;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public Boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

package com.dnsimple.data;

import java.time.OffsetDateTime;

public class DomainPush {
    private final Long id;
    private final Long domainId;
    private final Long contactId;
    private final Long accountId;
    private final OffsetDateTime acceptedAt;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public DomainPush(Long id, Long domainId, Long contactId, Long accountId, OffsetDateTime acceptedAt, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.contactId = contactId;
        this.accountId = accountId;
        this.acceptedAt = acceptedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public Long getContactId() {
        return contactId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public OffsetDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

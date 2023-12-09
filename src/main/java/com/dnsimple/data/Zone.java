package com.dnsimple.data;

import java.time.OffsetDateTime;

public class Zone {
    private final Long id;
    private final Long accountId;
    private final String name;
    private final Boolean reverse;
    private final Boolean secondary;
    private final OffsetDateTime lastTransferredAt;
    private final Boolean active;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public Zone(Long id, Long accountId, String name, Boolean reverse, Boolean secondary, OffsetDateTime lastTransferredAt, Boolean active, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.reverse = reverse;
        this.secondary = secondary;
        this.lastTransferredAt = lastTransferredAt;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public Boolean isReverse() {
        return reverse;
    }

    public Boolean isSecondary() {
        return secondary;
    }

    public OffsetDateTime getLastTransferredAt() {
        return lastTransferredAt;
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

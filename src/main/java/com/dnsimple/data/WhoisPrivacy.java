package com.dnsimple.data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class WhoisPrivacy {
    private final Long id;
    private final Long domainId;
    private final Boolean enabled;
    private final LocalDate expiresOn;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public WhoisPrivacy(Long id, Long domainId, Boolean enabled, LocalDate expiresOn, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.enabled = enabled;
        this.expiresOn = expiresOn;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public LocalDate getExpiresOn() {
        return expiresOn;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

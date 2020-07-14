package com.dnsimple.data;

import java.time.OffsetDateTime;

public class DomainRenewal {
    private final Long id;
    private final Long domainId;
    private final Integer period;
    private final String state;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public DomainRenewal(Long id, Long domainId, Integer period, String state, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.period = period;
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

    public Integer getPeriod() {
        return period;
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

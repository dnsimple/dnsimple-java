package com.dnsimple.data;

import java.time.OffsetDateTime;

public class DomainRegistration {
    private final Long id;
    private final Long domainId;
    private final Long registrantId;
    private final Integer period;
    private final String state;
    private final Boolean autoRenew;
    private final Boolean whoisPrivacy;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public DomainRegistration(Long id, Long domainId, Long registrantId, Integer period, String state, Boolean autoRenew, Boolean whoisPrivacy, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.registrantId = registrantId;
        this.period = period;
        this.state = state;
        this.autoRenew = autoRenew;
        this.whoisPrivacy = whoisPrivacy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public Long getRegistrantId() {
        return registrantId;
    }

    public Integer getPeriod() {
        return period;
    }

    public String getState() {
        return state;
    }

    public Boolean hasAutoRenew() {
        return autoRenew;
    }

    public Boolean hasWhoisPrivacy() {
        return whoisPrivacy;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

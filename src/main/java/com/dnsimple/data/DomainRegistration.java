package com.dnsimple.data;

public class DomainRegistration {
    private final Integer id;
    private final Integer domainId;
    private final Integer registrantId;
    private final Integer period;
    private final String state;
    private final boolean autoRenew;
    private final boolean whoisPrivacy;
    private final String createdAt;
    private final String updatedAt;

    public DomainRegistration(Integer id, Integer domainId, Integer registrantId, Integer period, String state, boolean autoRenew, boolean whoisPrivacy, String createdAt, String updatedAt) {
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

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getRegistrantId() {
        return registrantId;
    }

    public Integer getPeriod() {
        return period;
    }

    public String getState() {
        return state;
    }

    public boolean hasAutoRenew() {
        return autoRenew;
    }

    public boolean hasWhoisPrivacy() {
        return whoisPrivacy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

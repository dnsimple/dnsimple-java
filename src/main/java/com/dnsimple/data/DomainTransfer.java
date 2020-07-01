package com.dnsimple.data;

public class DomainTransfer {
    private final Integer id;
    private final Integer domainId;
    private final Integer registrantId;
    private final String state;
    private final boolean autoRenew;
    private final boolean whoisPrivacy;
    private final String statusDescription;
    private final String createdAt;
    private final String updatedAt;

    public DomainTransfer(Integer id, Integer domainId, Integer registrantId, String state, boolean autoRenew, boolean whoisPrivacy, String statusDescription, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.registrantId = registrantId;
        this.state = state;
        this.autoRenew = autoRenew;
        this.whoisPrivacy = whoisPrivacy;
        this.statusDescription = statusDescription;
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

    public String getState() {
        return state;
    }

    public boolean hasAutoRenew() {
        return autoRenew;
    }

    public boolean hasWhoisPrivacy() {
        return whoisPrivacy;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

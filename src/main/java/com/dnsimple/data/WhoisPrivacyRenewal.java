package com.dnsimple.data;

public class WhoisPrivacyRenewal {
    private final Integer id;
    private final Integer domainId;
    private final Integer whoisPrivacyId;
    private final String state;
    private final Boolean enabled;
    private final String expiresOn;
    private final String createdAt;
    private final String updatedAt;

    public WhoisPrivacyRenewal(Integer id, Integer domainId, Integer whoisPrivacyId, String state, Boolean enabled, String expiresOn, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.whoisPrivacyId = whoisPrivacyId;
        this.state = state;
        this.enabled = enabled;
        this.expiresOn = expiresOn;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getWhoisPrivacyId() {
        return whoisPrivacyId;
    }

    public String getState() {
        return state;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getExpiresOn() {
        return expiresOn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

package com.dnsimple.data;

public class DomainRegistration {
    private Integer id;
    private Integer domainId;
    private Integer registrantId;
    private Integer period;
    private String state;
    private boolean autoRenew;
    private boolean whoisPrivacy;
    private String createdAt;
    private String updatedAt;

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

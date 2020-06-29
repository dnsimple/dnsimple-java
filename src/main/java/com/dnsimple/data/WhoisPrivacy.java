package com.dnsimple.data;

public class WhoisPrivacy {
    private final Integer id;
    private final Integer domainId;
    private final String expiresOn;
    private final Boolean enabled;
    private final String createdAt;
    private final String updatedAt;

    public WhoisPrivacy(Integer id, Integer domainId, String expiresOn, Boolean enabled, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.expiresOn = expiresOn;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getExpiresOn() {
        return expiresOn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

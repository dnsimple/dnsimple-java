package com.dnsimple.data;

public class Dnssec {
    private final Boolean enabled;
    private final String createdAt;
    private final String updatedAt;

    public Dnssec(Boolean enabled, String createdAt, String updatedAt) {
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

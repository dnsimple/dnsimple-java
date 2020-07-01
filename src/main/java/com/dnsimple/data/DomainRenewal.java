package com.dnsimple.data;

public class DomainRenewal {
    private final Integer id;
    private final Integer domainId;
    private final Integer period;
    private final String state;
    private final String createdAt;
    private final String updatedAt;

    public DomainRenewal(Integer id, Integer domainId, Integer period, String state, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.period = period;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getPeriod() {
        return period;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

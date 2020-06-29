package com.dnsimple.data;

public class EmailForward {
    private final Integer id;
    private final Integer domainId;
    private final String from;
    private final String to;
    private final String createdAt;
    private final String updatedAt;

    public EmailForward(Integer id, Integer domainId, String from, String to, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.from = from;
        this.to = to;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

package com.dnsimple.data;

import java.time.OffsetDateTime;

public class EmailForward {
    private final Long id;
    private final Long domainId;
    private final String from;
    private final String to;
    private final String aliasEmail;
    private final String destinationEmail;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public EmailForward(Long id, Long domainId, String from, String to, String aliasEmail, String destinationEmail, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.from = from;
        this.to = to;
        this.aliasEmail = aliasEmail;
        this.destinationEmail = destinationEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAliasEmail() {
        return aliasEmail;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

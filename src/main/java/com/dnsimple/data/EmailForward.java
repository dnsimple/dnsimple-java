package com.dnsimple.data;

import java.time.OffsetDateTime;

public class EmailForward {
    private final Long id;
    private final Long domainId;
    private final String aliasEmail;
    private final String destinationEmail;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public EmailForward(Long id, Long domainId, String aliasEmail, String destinationEmail, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
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

    /**
     * @deprecated use {@link #getAliasEmail()} instead
     */
    @Deprecated(since = "0.10.0", forRemoval = true)
    public String getFrom() {
        return aliasEmail;
    }

    /**
     * @deprecated use {@link #getDestinationEmail} instead
     */
    @Deprecated(since = "0.10.0", forRemoval = true)
    public String getTo() {
        return destinationEmail;
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

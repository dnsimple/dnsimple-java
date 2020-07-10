package com.dnsimple.data;

import java.time.OffsetDateTime;

public class Collaborator {
    private final Long id;
    private final Long domainId;
    private final String domainName;
    private final Long userId;
    private final String userEmail;
    private final Boolean invitation;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final OffsetDateTime acceptedAt;

    public Collaborator(Long id, Long domainId, String domainName, Long userId, String userEmail, Boolean invitation, OffsetDateTime createdAt, OffsetDateTime updatedAt, OffsetDateTime acceptedAt) {
        this.id = id;
        this.domainId = domainId;
        this.domainName = domainName;
        this.userId = userId;
        this.userEmail = userEmail;
        this.invitation = invitation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.acceptedAt = acceptedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Boolean hasInvitation() {
        return invitation;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OffsetDateTime getAcceptedAt() {
        return acceptedAt;
    }
}

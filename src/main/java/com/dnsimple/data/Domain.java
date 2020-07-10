package com.dnsimple.data;

import java.time.OffsetDateTime;

public class Domain {
    private final Long id;
    private final Long accountId;
    private final Long registrantId;
    private final String name;
    private final String unicodeName;
    private final String token;
    private final String state;
    private final Boolean autoRenew;
    private final Boolean privateWhois;
    private final OffsetDateTime expiresAt;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public Domain(Long id, Long accountId, Long registrantId, String name, String unicodeName, String token, String state, Boolean autoRenew, Boolean privateWhois, OffsetDateTime expiresAt, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.registrantId = registrantId;
        this.name = name;
        this.unicodeName = unicodeName;
        this.token = token;
        this.state = state;
        this.autoRenew = autoRenew;
        this.privateWhois = privateWhois;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getRegistrantId() {
        return registrantId;
    }

    public String getName() {
        return name;
    }

    public String getUnicodeName() {
        return unicodeName;
    }

    public String getToken() {
        return token;
    }

    public String getState() {
        return state;
    }

    public Boolean hasAutoRenew() {
        return autoRenew;
    }

    public Boolean hasPrivateWhois() {
        return privateWhois;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

package com.dnsimple.data;

import java.time.OffsetDateTime;

public class Account {
    private final Long id;
    private final String email;
    private final String name;
    private final String planIdentifier;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public Account(Long id, String email, String name, String planIdentifier, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.planIdentifier = planIdentifier;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPlanIdentifier() {
        return planIdentifier;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

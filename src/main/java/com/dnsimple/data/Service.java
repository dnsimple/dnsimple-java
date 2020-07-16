package com.dnsimple.data;

import java.time.OffsetDateTime;
import java.util.List;

public class Service {
    private final Long id;
    private final String sid;
    private final String name;
    private final String description;
    private final String setupDescription;
    private final Boolean requiresSetup;
    private final String defaultSubdomain;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final List<ServiceSetting> settings;

    public Service(Long id, String sid, String name, String description, String setupDescription, Boolean requiresSetup, String defaultSubdomain, OffsetDateTime createdAt, OffsetDateTime updatedAt, List<ServiceSetting> settings) {
        this.id = id;
        this.sid = sid;
        this.name = name;
        this.description = description;
        this.setupDescription = setupDescription;
        this.requiresSetup = requiresSetup;
        this.defaultSubdomain = defaultSubdomain;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.settings = settings;
    }

    public Long getId() {
        return id;
    }

    public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSetupDescription() {
        return setupDescription;
    }

    public Boolean requiresSetup() {
        return requiresSetup;
    }

    public String getDefaultSubdomain() {
        return defaultSubdomain;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<ServiceSetting> getSettings() {
        return settings;
    }
}


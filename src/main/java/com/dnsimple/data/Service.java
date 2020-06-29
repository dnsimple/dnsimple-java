package com.dnsimple.data;

import java.util.List;

public class Service {
    private final Integer id;
    private final String name;
    private final String sid;
    private final String description;
    private final String setupDescription;
    private final Boolean requiresSetup;
    private final String defaultSubdomain;
    private final String createdAt;
    private final String updatedAt;
    private final List<ServiceSetting> settings;

    public Service(Integer id, String name, String sid, String description, String setupDescription, Boolean requiresSetup, String defaultSubdomain, String createdAt, String updatedAt, List<ServiceSetting> settings) {
        this.id = id;
        this.name = name;
        this.sid = sid;
        this.description = description;
        this.setupDescription = setupDescription;
        this.requiresSetup = requiresSetup;
        this.defaultSubdomain = defaultSubdomain;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.settings = settings;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSid() {
        return sid;
    }

    public String getDescription() {
        return description;
    }

    public String getSetupDescription() {
        return setupDescription;
    }

    public Boolean getRequiresSetup() {
        return requiresSetup;
    }

    public String getDefaultSubdomain() {
        return defaultSubdomain;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<ServiceSetting> getSettings() {
        return settings;
    }
}


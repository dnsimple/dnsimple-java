package com.dnsimple.data;

import java.util.List;

public class Service {
    private Integer id;
    private String name;
    private String sid;
    private String description;
    private String setupDescription;
    private Boolean requiresSetup;
    private String defaultSubdomain;
    private String createdAt;
    private String updatedAt;
    private List<ServiceSetting> settings;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
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


package com.dnsimple;

import java.util.List;

import com.google.api.client.util.Key;

public class Service {
  @Key("id")
  private Integer id;

  @Key("name")
  private String name;

  @Key("short_name")
  private String shortName;

  @Key("description")
  private String description;

  @Key("setup_description")
  private String setupDescription;

  @Key("requires_setup")
  private Boolean requiresSetup;

  @Key("default_subdomain")
  private String defaultSubdomain;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  @Key("settings")
  private List<ServiceSetting> settings;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getShortName() {
    return shortName;
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


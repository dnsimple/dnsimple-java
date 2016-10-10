package com.dnsimple.data;

import java.util.List;

import com.google.api.client.util.Key;

public class TldExtendedAttribute {
  @Key("name")
  private String name;

  @Key("description")
  private String description;

  @Key("required")
  private Boolean required;

  @Key("options")
  private List<TldExtendedAttributeOption> options;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getRequired() {
    return required;
  }

  public List<TldExtendedAttributeOption> getOptions() {
    return options;
  }
}

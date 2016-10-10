package com.dnsimple.data;

import com.google.api.client.util.Key;

public class TldExtendedAttributeOption {
  @Key("title")
  private String title;

  @Key("value")
  private String value;

  @Key("description")
  private String description;

  public String getTitle() {
    return title;
  }

  public String getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }
}

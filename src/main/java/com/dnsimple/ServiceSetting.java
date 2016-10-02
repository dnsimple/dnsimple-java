package com.dnsimple;

import com.google.api.client.util.Key;

public class ServiceSetting {

  @Key("name")
  private String name;

  @Key("label")
  private String label;

  @Key("append")
  private String append;

  @Key("description")
  private String description;

  @Key("example")
  private String example;

  @Key("password")
  private Boolean password;

  public String getName() {
    return name;
  }

  public String getLabel() {
    return label;
  }

  public String getAppend() {
    return append;
  }

  public String getDescription() {
    return description;
  }

  public String getExample() {
    return example;
  }

  public Boolean getPassword() {
    return password;
  }

}

package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Account {
  @Key("id")
  private Integer id;

  @Key("email")
  private String email;

  public Integer getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}

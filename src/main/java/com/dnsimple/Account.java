package com.dnsimple;

import com.google.api.client.util.Key;

public class Account {
  @Key("id")
  private Integer id;

  @Key("email")
  private String email;

  public Account() {}

  public Integer getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}

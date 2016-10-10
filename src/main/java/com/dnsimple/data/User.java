package com.dnsimple.data;

import com.google.api.client.util.Key;

public class User {
  @Key("id")
  private Integer id;

  @Key("email")
  private String email;

  public User() {} 

  public Integer getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}


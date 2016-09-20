package com.dnsimple;

import com.google.api.client.util.Key;

public class User {
  @Key("id")
  private int id;

  @Key("email")
  private String email;

  public User() {}
  public User(int id, String email) {
    this.id = id;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}


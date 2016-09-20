package com.dnsimple;

import com.google.api.client.util.Key;

public class Account {
  @Key
  private int id;
  @Key
  private String email;

  public Account() {}
  public Account(int id, String email) {
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

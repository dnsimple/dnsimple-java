package com.dnsimple;

import com.google.api.client.util.Key;

public class Whoami {
  @Key("account")
  private Account account;
  @Key("user")
  private User user;

  public Whoami() { }

  public Account getAccount() {
    return account;
  }

  public User getUser() {
    return user;
  }
}

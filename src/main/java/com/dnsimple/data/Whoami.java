package com.dnsimple.data;

import com.google.api.client.util.Key;

public class Whoami {
  @Key("account")
  private Account account;
  @Key("user")
  private User user;

  public Whoami() {}

  public Whoami(Account account) {
    this.account = account;
  }

  public Whoami(User user) {
    this.user = user;
  }

  public Account getAccount() {
    return account;
  }

  public User getUser() {
    return user;
  }
}

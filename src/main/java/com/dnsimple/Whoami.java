package com.dnsimple;

import com.google.api.client.util.Key;

public class Whoami {
  @Key("account")
  private Account account;

  public Whoami() { }
  public Whoami(Account account) {
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}

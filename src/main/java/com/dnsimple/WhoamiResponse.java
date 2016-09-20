package com.dnsimple;

import com.google.api.client.util.Key;

public class WhoamiResponse implements ResponseData {
  @Key("account")
  private Account account;

  public WhoamiResponse() { }
  public WhoamiResponse(Account account) {
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}

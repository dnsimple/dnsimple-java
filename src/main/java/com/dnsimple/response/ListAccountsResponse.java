package com.dnsimple.response;

import com.dnsimple.Account;

import java.util.List;

import com.google.api.client.util.Key;

public class ListAccountsResponse extends ApiResponse {
  @Key("data")
  private List<Account> data;

  public List<Account> getData() {
    return data;
  }

}

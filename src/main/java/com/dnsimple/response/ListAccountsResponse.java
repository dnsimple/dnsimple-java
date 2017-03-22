package com.dnsimple.response;

import com.dnsimple.data.Account;

import java.util.List;
import java.util.ArrayList;

import com.google.api.client.util.Key;

public class ListAccountsResponse extends ApiResponse {
  @Key("data")
  private List<Account> data;

  public ListAccountsResponse() {
    this(new ArrayList<Account>());
  }

  public ListAccountsResponse(List<Account> data) {
    this.data = data;
  }

  public List<Account> getData() {
    return data;
  }

}

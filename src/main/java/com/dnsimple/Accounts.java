package com.dnsimple;

import java.io.IOException;
import java.util.List;

import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;


public class Accounts {
  private Client client;

  public Accounts(Client client) {
    this.client = client;
  }

  public ListAccountsResponse listAccounts() throws DnsimpleException, IOException {
    HttpResponse response = client.get("/accounts");
    return (ListAccountsResponse)client.parseResponse(response, ListAccountsResponse.class);
  }
}

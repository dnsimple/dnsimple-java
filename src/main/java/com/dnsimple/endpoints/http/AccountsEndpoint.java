package com.dnsimple.endpoints.http;

import com.dnsimple.Accounts;
import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.List;

public class AccountsEndpoint implements Accounts {
  private HttpEndpointClient client;

  public AccountsEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListAccountsResponse listAccounts() throws DnsimpleException, IOException {
    HttpResponse response = client.get("/accounts");
    return (ListAccountsResponse)client.parseResponse(response, ListAccountsResponse.class);
  }
}

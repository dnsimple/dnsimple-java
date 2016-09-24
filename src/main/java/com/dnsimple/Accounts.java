package com.dnsimple;

import java.io.IOException;
import java.util.List;

import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

/**
 * Provides access to the DNSimple Accounts API.
 *
 * @see https://developer.dnsimple.com/v2/accounts
 */
public class Accounts {
  private Client client;

  protected Accounts(Client client) {
    this.client = client;
  }

  /**
   * Lists the accounts the authenticated entity has access to.
   *
   * @see https://developer.dnsimple.com/v2/accounts#list
   *
   * @return The list accounts response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListAccountsResponse listAccounts() throws DnsimpleException, IOException {
    HttpResponse response = client.get("/accounts");
    return (ListAccountsResponse)client.parseResponse(response, ListAccountsResponse.class);
  }
}

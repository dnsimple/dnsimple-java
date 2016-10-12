package com.dnsimple.endpoints.test;

import com.dnsimple.Accounts;
import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

public class AccountsEndpoint implements Accounts {
  public ListAccountsResponse listAccounts() throws DnsimpleException, IOException {
    return new ListAccountsResponse();
  }
}

package com.dnsimple;

import com.dnsimple.data.Account;
import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import com.dnsimple.tools.HttpMethod;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.api.client.http.HttpMethods;

import static org.junit.Assert.assertEquals;

public class AccountsTest extends DnsimpleTestBase {

  @Test
  public void testListAccounts() throws DnsimpleException, IOException {
    server.expectGet("/v2/accounts");
    server.stubFixtureAt("listAccounts/success-account.http");
    Client client = new Client();

    ListAccountsResponse response = client.accounts.listAccounts();

    List<Account> accounts = response.getData();
    assertEquals(1, accounts.size());
    assertEquals(1324, accounts.get(0).getId().intValue());
  }

}

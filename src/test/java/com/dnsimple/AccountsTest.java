package com.dnsimple;

import com.dnsimple.data.Account;
import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;

import static org.junit.Assert.assertEquals;

public class AccountsTest extends DnsimpleTestBase {

  @Test
  public void testListAccounts() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/accounts", HttpMethods.GET, resource("listAccounts/success.http"));

    ListAccountsResponse response = client.accounts.listAccounts();

    List<Account> accounts = response.getData();
    assertEquals(1, accounts.size());
    assertEquals(123, accounts.get(0).getId().intValue());
  }

}

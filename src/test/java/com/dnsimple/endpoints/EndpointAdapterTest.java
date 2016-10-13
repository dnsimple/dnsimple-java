package com.dnsimple.endpoints;

import com.dnsimple.Client;
import com.dnsimple.Accounts;
import com.dnsimple.data.Account;
import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class EndpointAdapterTest {

  @Test
  public void testListAccounts() throws DnsimpleException, IOException {
    EndpointAdapter adapter = mock(EndpointAdapter.class);
    when(adapter.accounts()).thenReturn(mock(Accounts.class));
    when(adapter.accounts().listAccounts()).thenReturn(new ListAccountsResponse());

    Client client = new Client(adapter);
    ListAccountsResponse response = client.accounts.listAccounts();
    List<Account> accounts = response.getData();
    assertEquals(0, accounts.size());
  }

  @Test
  public void testListAccountsWithMock() throws DnsimpleException, IOException {
    List<Account> data = new ArrayList<Account>();

    EndpointAdapter adapter = mock(EndpointAdapter.class);
    when(adapter.accounts()).thenReturn(mock(Accounts.class));
    when(adapter.accounts().listAccounts()).thenReturn(new ListAccountsResponse(data));

    Client client = new Client(adapter);
    ListAccountsResponse listAccountsResponse = client.accounts.listAccounts();
    List<Account> accounts = listAccountsResponse.getData();
    assertEquals(data, accounts);
  }

}

package com.dnsimple.endpoints;

import com.dnsimple.Client;
import com.dnsimple.Accounts;
import com.dnsimple.Identity;
import com.dnsimple.Domains;
import com.dnsimple.data.Account;
import com.dnsimple.data.Domain;
import com.dnsimple.data.Whoami;
import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.WhoamiResponse;
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
  public void testListAccountsWithData() throws DnsimpleException, IOException {
    List<Account> data = new ArrayList<Account>();

    EndpointAdapter adapter = mock(EndpointAdapter.class);
    when(adapter.accounts()).thenReturn(mock(Accounts.class));
    when(adapter.accounts().listAccounts()).thenReturn(new ListAccountsResponse(data));

    Client client = new Client(adapter);
    ListAccountsResponse listAccountsResponse = client.accounts.listAccounts();
    List<Account> accounts = listAccountsResponse.getData();
    assertEquals(data, accounts);
  }

  @Test
  public void testMultipleCalls() throws DnsimpleException, IOException {
    Account account = new Account(1);

    EndpointAdapter adapter = mock(EndpointAdapter.class);
    when(adapter.identity()).thenReturn(mock(Identity.class));
    when(adapter.identity().whoami()).thenReturn(new WhoamiResponse(new Whoami(account)));

    List<Domain> domains = new ArrayList<Domain>();
    domains.add(new Domain());
    when(adapter.domains()).thenReturn(mock(Domains.class));
    when(adapter.domains().listDomains(account.getId().toString())).thenReturn(new ListDomainsResponse(domains));

    Client client = new Client(adapter);
    WhoamiResponse whoamiResponse = client.identity.whoami();
    assertEquals(account, whoamiResponse.getData().getAccount());

    ListDomainsResponse listDomainsResponse = client.domains.listDomains(account.getId().toString());
    assertEquals(domains, listDomainsResponse.getData());
  }

}

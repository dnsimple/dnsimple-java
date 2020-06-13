package com.dnsimple;

import static com.dnsimple.tools.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.dnsimple.data.Account;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListAccountsResponse;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class AccountsTest extends DnsimpleTestBase {

  @Test
  public void testListAccounts() throws DnsimpleException, IOException {
    server.stubFixtureAt("listAccounts/success-account.http");

    ListAccountsResponse response = client.accounts.listAccounts();
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));

    List<Account> accounts = response.getData();
    assertThat(accounts, hasSize(1));
    assertThat(accounts.get(0).getId(), is(123));
  }

}

package com.dnsimple;

import com.dnsimple.data.Account;
import com.dnsimple.response.ListResponse;
import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class AccountsTest extends DnsimpleTestBase {
    @Test
    public void testListAccounts() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listAccounts/success-account.http");
        ListResponse<Account> response = client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        List<Account> accounts = response.getData();
        assertThat(accounts, hasSize(1));
        assertThat(accounts.get(0).getId(), is(123));
    }
}

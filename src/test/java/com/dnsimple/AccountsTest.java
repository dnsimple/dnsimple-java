package com.dnsimple;

import com.dnsimple.data.Account;
import com.dnsimple.response.ListResponse;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.time.ZoneOffset.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class AccountsTest extends DnsimpleTestBase {
    @Test
    public void testListAccounts() {
        server.stubFixtureAt("listAccounts/success-account.http");
        ListResponse<Account> response = client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        List<Account> accounts = response.getData();
        assertThat(accounts, hasSize(1));
        assertThat(accounts.get(0).getId(), is(123L));
        assertThat(accounts.get(0).getEmail(), is("john@example.com"));
        assertThat(accounts.get(0).getPlanIdentifier(), is("dnsimple-personal"));
        assertThat(accounts.get(0).getCreatedAt(), is(OffsetDateTime.of(2011, 9, 11, 17, 15, 58, 0, UTC)));
        assertThat(accounts.get(0).getUpdatedAt(), is(OffsetDateTime.of(2016, 6, 3, 15, 02, 26, 0, UTC)));
    }
}

package com.dnsimple;

import org.junit.Test;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

public class ClientTest extends DnsimpleTestBase {
    @Test
    public void testAuthorizationHeader() {
        server.stubFixtureAt("listAccounts/success-account.http");
        client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        assertThat(server.getRecordedRequest().getHeaders(), hasEntry("Authorization", "Bearer " + TEST_ACCESS_TOKEN));
    }

    @Test
    public void testUserAgentHeader() {
        server.stubFixtureAt("listAccounts/success-account.http");
        client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        assertThat(server.getRecordedRequest().getHeaders(), hasEntry("User-Agent", TEST_USER_AGENT + " dnsimple-java/" + Dnsimple.VERSION));
    }
}

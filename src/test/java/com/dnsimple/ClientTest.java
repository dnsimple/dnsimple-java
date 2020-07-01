package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;

import static com.dnsimple.tools.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

public class ClientTest extends DnsimpleTestBase {
    @Test
    public void testAuthorizationHeader() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listAccounts/success-account.http");
        client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        assertThat(server.getRecordedRequest().getHeaders(), hasEntry("Authorization", "Bearer " + TEST_ACCESS_TOKEN));
    }

    @Test
    public void testUserAgentHeader() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listAccounts/success-account.http");
        client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        assertThat(server.getRecordedRequest().getHeaders(), hasEntry("User-Agent", TEST_USER_AGENT + " dnsimple-java/" + Dnsimple.VERSION));
    }
}

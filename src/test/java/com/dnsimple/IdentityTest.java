package com.dnsimple;

import com.dnsimple.data.Whoami;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.SimpleResponse;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class IdentityTest extends DnsimpleTestBase {
    @Test
    public void testWhoamiWithAccount() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("whoami/success-account.http");
        Whoami data = client.identity.whoami().getData();
        assertThat(data.getUser(), is(nullValue()));
        assertThat(data.getAccount().getId(), is(1));
        assertThat(data.getAccount().getEmail(), is("example-account@example.com"));
    }

    @Test
    public void testWhoamiWithUser() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("whoami/success-user.http");
        SimpleResponse<Whoami> whoami = client.identity.whoami();
        Whoami data = whoami.getData();
        assertThat(data.getUser().getId(), is(1));
        assertThat(data.getUser().getEmail(), is("example-user@example.com"));
        assertThat(data.getAccount(), is(nullValue()));
    }
}

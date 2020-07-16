package com.dnsimple.endpoints;

import com.dnsimple.data.WhoamiData;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class IdentityTest extends DnsimpleTestBase {
    @Test
    public void testWhoamiWithAccount() {
        server.stubFixtureAt("whoami/success-account.http");
        WhoamiData data = client.identity.whoami().getData();
        assertThat(data.getUser(), is(nullValue()));
        assertThat(data.getAccount().getId(), is(1L));
        assertThat(data.getAccount().getEmail(), is("example-account@example.com"));
    }

    @Test
    public void testWhoamiWithUser() {
        server.stubFixtureAt("whoami/success-user.http");
        SimpleResponse<WhoamiData> whoami = client.identity.whoami();
        WhoamiData data = whoami.getData();
        assertThat(data.getUser().getId(), is(1L));
        assertThat(data.getUser().getEmail(), is("example-user@example.com"));
        assertThat(data.getAccount(), is(nullValue()));
    }
}

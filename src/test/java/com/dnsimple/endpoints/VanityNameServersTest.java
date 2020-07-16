package com.dnsimple.endpoints;

import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import static com.dnsimple.http.HttpMethod.DELETE;
import static com.dnsimple.http.HttpMethod.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VanityNameServersTest extends DnsimpleTestBase {
    @Test
    public void testEnableVanityNameServers() {
        server.stubFixtureAt("enableVanityNameServers/success.http");
        client.vanityNameServers.enableVanityNameServers(1010, "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/vanity/example.com"));
    }

    @Test
    public void testDisableVanityNameServers() {
        server.stubFixtureAt("disableVanityNameServers/success.http");
        client.vanityNameServers.disableVanityNameServers(1010, "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/vanity/example.com"));
    }
}

package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;

import static com.dnsimple.endpoints.http.java11.HttpMethod.DELETE;
import static com.dnsimple.endpoints.http.java11.HttpMethod.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VanityNameServersTest extends DnsimpleTestBase {
    @Test
    public void testEnableVanityNameServers() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("enableVanityNameServers/success.http");
        client.vanityNameServers.enableVanityNameServers("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/vanity/example.com"));
    }

    @Test
    public void testDisableVanityNameServers() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("disableVanityNameServers/success.http");
        client.vanityNameServers.disableVanityNameServers("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/vanity/example.com"));
    }
}

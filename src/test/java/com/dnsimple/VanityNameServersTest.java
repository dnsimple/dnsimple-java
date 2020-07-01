package com.dnsimple;

import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.DisableVanityNameServersResponse;
import java.io.IOException;
import org.junit.Test;

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

    DisableVanityNameServersResponse response = client.vanityNameServers.disableVanityNameServers("1010", "example.com");
    assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/vanity/example.com"));
    assertThat(response.getData(), is(nullValue()));
  }
}

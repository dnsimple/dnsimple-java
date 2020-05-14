package com.dnsimple;

import com.dnsimple.data.NameServer;
import com.dnsimple.response.EnableVanityNameServersResponse;
import com.dnsimple.response.DisableVanityNameServersResponse;
import com.dnsimple.exception.DnsimpleException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;

import java.io.IOException;
import java.util.List;

public class VanityNameServersTest extends DnsimpleTestBase {
  @Test
  public void testEnableVanityNameServers() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectPut("/v2/1010/vanity/example.com");
    server.stubFixtureAt("enableVanityNameServers/success.http");
    Client client = new Client();

    EnableVanityNameServersResponse response = client.vanityNameServers.enableVanityNameServers(accountId, domainId);
    List<NameServer> vanityNameServers = response.getData();
  }

  @Test
  public void testDisableVanityNameServers() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectDelete("/v2/1010/vanity/example.com");
    server.stubFixtureAt("disableVanityNameServers/success.http");
    Client client = new Client();

    DisableVanityNameServersResponse response = client.vanityNameServers.disableVanityNameServers(accountId, domainId);
    assertEquals(null, response.getData());
  }
}

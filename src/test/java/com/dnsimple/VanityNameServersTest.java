package com.dnsimple;

import com.dnsimple.request.Filter;

import com.dnsimple.response.EnableVanityNameServersResponse;
import com.dnsimple.response.DisableVanityNameServersResponse;

import com.dnsimple.exception.DnsimpleException;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

public class VanityNameServersTest extends DnsimpleTestBase {
  @Test
  public void testEnableVanityNameServers() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/vanity/example.com", HttpMethods.PUT, null, resource("enableVanityNameServers/success.http"));

    EnableVanityNameServersResponse response = client.vanityNameServers.enableVanityNameServers(accountId, domainId);
    List<NameServer> vanityNameServers = response.getData();
  }

  @Test
  public void testDisableVanityNameServers() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/vanity/example.com", HttpMethods.DELETE, null, resource("disableVanityNameServers/success.http"));

    DisableVanityNameServersResponse response = client.vanityNameServers.disableVanityNameServers(accountId, domainId);
    assertEquals(null, response.getData());
  }
}

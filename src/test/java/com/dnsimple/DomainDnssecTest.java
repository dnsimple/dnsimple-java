package com.dnsimple;

import com.dnsimple.data.Dnssec;
import com.dnsimple.response.EnableDnssecResponse;
import com.dnsimple.response.DisableDnssecResponse;
import com.dnsimple.response.GetDnssecResponse;
import com.dnsimple.exception.DnsimpleException;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

public class DomainDnssecTest extends DnsimpleTestBase {

  @Test
  public void testEnableDnssec() throws DnsimpleException, IOException {
    server.stubFixtureAt("enableDnssec/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    EnableDnssecResponse response = client.domains.enableDnssec(accountId, domainId);

    Dnssec dnssec = response.getData();
    assertEquals(true, dnssec.getEnabled().booleanValue());
  }

  @Test
  public void testDisableDnssec() throws DnsimpleException, IOException {
    server.stubFixtureAt("disableDnssec/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    DisableDnssecResponse response = client.domains.disableDnssec(accountId, domainId);

    assertEquals(null, response.getData());
  }

  @Test
  public void testDisableDnssecWhenNotEnabled() throws DnsimpleException, IOException {
    server.stubFixtureAt("disableDnssec/not-enabled.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    try {
      client.domains.disableDnssec(accountId, domainId);
      fail("Expected a DnsimpleException to be thrown");
    } catch (DnsimpleException e) {
      assertEquals(428, e.getStatusCode().intValue());
    }
  }

  @Test
  public void testGetDnssec() throws DnsimpleException, IOException {
    server.stubFixtureAt("getDnssec/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    GetDnssecResponse response = client.domains.getDnssec(accountId, domainId);

    Dnssec dnssec = response.getData();
    assertEquals(true, dnssec.getEnabled().booleanValue());
  }
}

package com.dnsimple;

import com.dnsimple.data.Dnssec;
import com.dnsimple.response.EnableDnssecResponse;
import com.dnsimple.response.DisableDnssecResponse;
import com.dnsimple.exception.DnsimpleException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;

public class DomainDnssecTest extends DnsimpleTestBase {

  @Test
  public void testEnableDnssec() throws DnsimpleException, IOException {
    Client client = mockClient(resource("enableDnssec/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    EnableDnssecResponse response = client.domains.enableDnssec(accountId, domainId);

    Dnssec dnssec = response.getData();
    assertEquals(true, dnssec.getEnabled().booleanValue());
  }

  @Test
  public void testDisableDnssec() throws DnsimpleException, IOException {
    Client client = mockClient(resource("disableDnssec/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    DisableDnssecResponse response = client.domains.disableDnssec(accountId, domainId);

    assertEquals(null, response.getData());
  }

  @Test
  public void testDisableDnssecWhenNotEnabled() throws DnsimpleException, IOException {
    Client client = mockClient(resource("disableDnssec/not-enabled.http"));

    String accountId = "1";
    String domainId = "example.com";

    try {
      client.domains.disableDnssec(accountId, domainId);
      fail("Expected a DnsimpleException to be thrown");
    } catch (DnsimpleException e) {
      assertEquals(428, e.getStatusCode().intValue());
    }
  }
}

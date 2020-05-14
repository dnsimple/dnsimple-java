package com.dnsimple;

import com.dnsimple.data.WhoisPrivacy;
import com.dnsimple.data.WhoisPrivacyRenewal;
import com.dnsimple.response.GetWhoisPrivacyResponse;
import com.dnsimple.response.EnableWhoisPrivacyResponse;
import com.dnsimple.response.DisableWhoisPrivacyResponse;
import com.dnsimple.response.RenewWhoisPrivacyResponse;
import com.dnsimple.exception.DnsimpleException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;

import java.io.IOException;

public class RegistrarWhoisPrivacyTest extends DnsimpleTestBase {
  @Test
  public void testGetWhoisPrivacy() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectGet("/v2/1010/registrar/domains/example.com/whois_privacy");
    server.stubFixtureAt("getWhoisPrivacy/success.http");
    Client client = new Client();

    GetWhoisPrivacyResponse response = client.registrar.getWhoisPrivacy(accountId, domainId);
    WhoisPrivacy whoisPrivacy = response.getData();
    assertEquals(1, whoisPrivacy.getId().intValue());
    assertEquals(2, whoisPrivacy.getDomainId().intValue());
    assertEquals("2017-02-13", whoisPrivacy.getExpiresOn());
    assertTrue(whoisPrivacy.getEnabled().booleanValue());
    assertEquals("2016-02-13T14:34:50Z", whoisPrivacy.getCreatedAt());
    assertEquals("2016-02-13T14:34:52Z", whoisPrivacy.getUpdatedAt());
  }

  @Test
  public void testEnableWhoisPrivacyAlreadyPurchased() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectPut("/v2/1010/registrar/domains/example.com/whois_privacy");
    server.stubFixtureAt("enableWhoisPrivacy/success.http");
    Client client = new Client();

    EnableWhoisPrivacyResponse response = client.registrar.enableWhoisPrivacy(accountId, domainId);
  }

  @Test
  public void testEnableWhoisPrivacyNewlyPurchased() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectPut("/v2/1010/registrar/domains/example.com/whois_privacy");
    server.stubFixtureAt("enableWhoisPrivacy/created.http");
    Client client = new Client();

    EnableWhoisPrivacyResponse response = client.registrar.enableWhoisPrivacy(accountId, domainId);
    WhoisPrivacy whoisPrivacy = response.getData();
    assertEquals(1, whoisPrivacy.getId().intValue());
  }

  @Test
  public void testDisableWhoisPrivacyNewlyPurchased() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectDelete("/v2/1010/registrar/domains/example.com/whois_privacy");
    server.stubFixtureAt("disableWhoisPrivacy/success.http");
    Client client = new Client();

    DisableWhoisPrivacyResponse response = client.registrar.disableWhoisPrivacy(accountId, domainId);
    WhoisPrivacy whoisPrivacy = response.getData();
    assertEquals(1, whoisPrivacy.getId().intValue());
  }

  @Test
  public void testRenewWhoisPrivacy() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectPost("/v2/1010/registrar/domains/example.com/whois_privacy/renewals");
    server.stubFixtureAt("renewWhoisPrivacy/success.http");
    Client client = new Client();

    RenewWhoisPrivacyResponse response = client.registrar.renewWhoisPrivacy(accountId, domainId);
    WhoisPrivacyRenewal whoisPrivacyRenewal = response.getData();
    assertEquals(1, whoisPrivacyRenewal.getId().intValue());
  }
}

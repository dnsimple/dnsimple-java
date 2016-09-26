package com.dnsimple;

import com.dnsimple.request.Filter;

import com.dnsimple.response.GetWhoisPrivacyResponse;
import com.dnsimple.response.EnableWhoisPrivacyResponse;
import com.dnsimple.response.DisableWhoisPrivacyResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

public class RegistrarWhoisPrivacyTest extends DnsimpleTestBase {
  @Test
  public void testGetWhoisPrivacy() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/whois_privacy", HttpMethods.GET, null, resource("getWhoisPrivacy/success.http"));

    GetWhoisPrivacyResponse response = client.registrar.getWhoisPrivacy(accountId, domainId);
    WhoisPrivacy whoisPrivacy = response.getData();
    assertEquals(1, whoisPrivacy.getId().intValue());
    assertEquals(2, whoisPrivacy.getDomainId().intValue());
    assertEquals("2017-02-13", whoisPrivacy.getExpiresOn());
    assertTrue(whoisPrivacy.getEnabled().booleanValue());
    assertEquals("2016-02-13T14:34:50.135Z", whoisPrivacy.getCreatedAt());
    assertEquals("2016-02-13T14:34:52.571Z", whoisPrivacy.getUpdatedAt());
  }

  @Test
  public void testEnableWhoisPrivacyAlreadyPurchased() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/whois_privacy", HttpMethods.PUT, null, resource("enableWhoisPrivacy/success.http"));

    EnableWhoisPrivacyResponse response = client.registrar.enableWhoisPrivacy(accountId, domainId);
  }

  @Test
  public void testEnableWhoisPrivacyNewlyPurchased() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/whois_privacy", HttpMethods.PUT, null, resource("enableWhoisPrivacy/created.http"));

    EnableWhoisPrivacyResponse response = client.registrar.enableWhoisPrivacy(accountId, domainId);
    WhoisPrivacy whoisPrivacy = response.getData();
    assertEquals(1, whoisPrivacy.getId().intValue());
  }

  @Test
  public void testDisableWhoisPrivacyNewlyPurchased() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/whois_privacy", HttpMethods.DELETE, null, resource("disableWhoisPrivacy/success.http"));

    DisableWhoisPrivacyResponse response = client.registrar.disableWhoisPrivacy(accountId, domainId);
    WhoisPrivacy whoisPrivacy = response.getData();
    assertEquals(1, whoisPrivacy.getId().intValue());
  }
}

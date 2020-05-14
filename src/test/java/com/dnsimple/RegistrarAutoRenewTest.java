package com.dnsimple;

import com.dnsimple.response.EnableAutoRenewalResponse;
import com.dnsimple.response.DisableAutoRenewalResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;

import java.io.IOException;

public class RegistrarAutoRenewTest extends DnsimpleTestBase {

  @Test
  public void testEnableAutoRenewal() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectPut("/v2/1010/registrar/domains/example.com/auto_renewal");
    server.stubFixtureAt("enableDomainAutoRenewal/success.http");
    Client client = new Client();

    EnableAutoRenewalResponse response = client.registrar.enableAutoRenewal(accountId, domainId);
    assertEquals(null, response.getData());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testEnableAutoRenewalDomainDoesNotExist() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "0";

    server.expectPut("/v2/1010/registrar/domains/0/auto_renewal");
    server.stubFixtureAt("notfound-domain.http");
    Client client = new Client();

    client.registrar.enableAutoRenewal(accountId, domainId);
  }

  @Test
  public void testDisableAutoRenewal() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectDelete("/v2/1010/registrar/domains/example.com/auto_renewal");
    server.stubFixtureAt("disableDomainAutoRenewal/success.http");
    Client client = new Client();

    DisableAutoRenewalResponse response = client.registrar.disableAutoRenewal(accountId, domainId);
    assertEquals(null, response.getData());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testDisableAutoRenewalDomainDoesNotExist() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "0";

    server.expectDelete("/v2/1010/registrar/domains/0/auto_renewal");
    server.stubFixtureAt("notfound-domain.http");
    Client client = new Client();

    client.registrar.disableAutoRenewal(accountId, domainId);
  }
}

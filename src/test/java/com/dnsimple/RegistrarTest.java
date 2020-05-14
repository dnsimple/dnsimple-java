package com.dnsimple;

import com.dnsimple.data.DomainAvailability;
import com.dnsimple.data.DomainRegistration;
import com.dnsimple.data.DomainRenewal;
import com.dnsimple.data.DomainTransfer;
import com.dnsimple.response.CheckDomainResponse;
import com.dnsimple.response.RegisterDomainResponse;
import com.dnsimple.response.RenewDomainResponse;
import com.dnsimple.response.TransferDomainResponse;
import com.dnsimple.response.TransferDomainOutResponse;
import com.dnsimple.exception.DnsimpleException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;

import java.io.IOException;
import java.util.HashMap;

public class RegistrarTest extends DnsimpleTestBase {

  @Test
  public void testCheckDomain() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "ruby.codes";

    server.expectGet("/v2/1010/registrar/domains/ruby.codes/check");
    server.stubFixtureAt("checkDomain/success.http");
    Client client = new Client();

    CheckDomainResponse response = client.registrar.checkDomain(accountId, name);
    DomainAvailability availability = response.getData();
    assertEquals(name, availability.getDomainName());
    assertTrue(availability.getAvailable().booleanValue());
    assertTrue(availability.getPremium().booleanValue());
  }

  @Test
  public void testRegisterDomain() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("registrant_id", "10");

    server.expectPost("/v2/1010/registrar/domains/example.com/registrations");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("registerDomain/success.http");
    Client client = new Client();

    RegisterDomainResponse response = client.registrar.registerDomain(accountId, name, attributes);
    DomainRegistration registration = response.getData();
    assertEquals(1, registration.getId().intValue());
    assertEquals(999, registration.getDomainId().intValue());
    assertEquals(2, registration.getRegistrantId().intValue());
    assertEquals("new", registration.getState());
    assertFalse(registration.hasAutoRenew());
    assertFalse(registration.hasWhoisPrivacy());
    assertEquals("2016-12-09T19:35:31Z", registration.getCreatedAt());
    assertEquals("2016-12-09T19:35:31Z", registration.getUpdatedAt());
  }

  @Test
  public void testRenewDomain() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("period", "3");

    server.expectPost("/v2/1010/registrar/domains/example.com/renewals");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("renewDomain/success.http");
    Client client = new Client();

    RenewDomainResponse response = client.registrar.renewDomain(accountId, name, attributes);
    DomainRenewal domainRenewal = response.getData();
    assertEquals(1, domainRenewal.getId().intValue());
  }

  @Test(expected=DnsimpleException.class)
  public void testRenewDomainTooSoon() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("period", "3");

    server.expectPost("/v2/1010/registrar/domains/example.com/renewals");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("renewDomain/error-tooearly.http");
    Client client = new Client();

    client.registrar.renewDomain(accountId, name, attributes);
  }

  @Test
  public void testTransferDomain() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("registrant_id", "1");
    attributes.put("auth_info", "x1y2z3");

    server.expectPost("/v2/1010/registrar/domains/example.com/transfers");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("transferDomain/success.http");
    Client client = new Client();

    TransferDomainResponse response = client.registrar.transferDomain(accountId, name, attributes);
    DomainTransfer transfer = response.getData();
    assertEquals(1, transfer.getId().intValue());
  }

  @Test
  public void testGetDomainTransfer() throws DnsimpleException, IOException  {
    String accountId = "1010";
    String name = "example.com";
    String transferId = "42";
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/transfers/42", HttpMethods.GET, new HttpHeaders(), null, resource("getDomainTransfer/success.http"));

    TransferDomainResponse response = client.registrar.getDomainTransfer(accountId, name, transferId);
    DomainTransfer transfer = response.getData();

    assertEquals(42, transfer.getId().intValue());
    assertEquals(2, transfer.getDomainId().intValue());
    assertEquals(3, transfer.getRegistrantId().intValue());
    assertEquals("cancelled", transfer.getState());
    assertFalse(transfer.hasAutoRenew());
    assertFalse(transfer.hasWhoisPrivacy());
    assertEquals("Canceled by customer", transfer.getStatusDescription());
    assertEquals("2020-04-27T18:08:44Z", transfer.getCreatedAt());
    assertEquals("2020-04-27T18:20:01Z", transfer.getUpdatedAt());
  }

  @Test
  public void testCancelDomainTransfer() throws DnsimpleException, IOException  {
    String accountId = "1010";
    String name = "example.com";
    String transferId = "42";
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/transfers/42", HttpMethods.DELETE, new HttpHeaders(), null, resource("cancelDomainTransfer/success.http"));

    TransferDomainResponse response = client.registrar.cancelDomainTransfer(accountId, name, transferId);
    DomainTransfer transfer = response.getData();

    assertEquals(42, transfer.getId().intValue());
    assertEquals(6, transfer.getDomainId().intValue());
    assertEquals(1, transfer.getRegistrantId().intValue());
    assertEquals("transferring", transfer.getState());
    assertTrue(transfer.hasAutoRenew());
    assertFalse(transfer.hasWhoisPrivacy());
    assertTrue(transfer.getStatusDescription().isEmpty());
    assertEquals("2020-04-24T19:19:03Z", transfer.getCreatedAt());
    assertEquals("2020-04-24T19:19:15Z", transfer.getUpdatedAt());
  }

  @Test(expected=DnsimpleException.class)
  public void testTransferDomainAlreadyInDnsimple() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("registrant_id", "1");
    attributes.put("auth_info", "x1y2z3");

    server.expectPost("/v2/1010/registrar/domains/example.com/transfers");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("transferDomain/error-indnsimple.http");
    Client client = new Client();

    client.registrar.transferDomain(accountId, name, attributes);
  }

  @Test(expected=DnsimpleException.class)
  public void testTransferDomainAuthInfoRequired() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("registrant_id", "1");

    server.expectPost("/v2/1010/registrar/domains/example.com/transfers");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("transferDomain/error-missing-authcode.http");
    Client client = new Client();

    client.registrar.transferDomain(accountId, name, attributes);
  }

  @Test
  public void testTransferDomainOut() throws DnsimpleException, IOException {
    String accountId = "1010";
    String name = "example.com";

    server.expectPost("/v2/1010/registrar/domains/example.com/authorize_transfer_out");
    server.stubFixtureAt("authorizeDomainTransferOut/success.http");
    Client client = new Client();

    TransferDomainOutResponse response = client.registrar.transferDomainOut(accountId, name);
    assertEquals(null, response.getData());
  }

}

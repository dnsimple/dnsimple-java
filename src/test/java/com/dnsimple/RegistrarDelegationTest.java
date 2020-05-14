package com.dnsimple;

import com.dnsimple.data.NameServer;
import com.dnsimple.response.GetDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationToVanityResponse;
import com.dnsimple.response.ChangeDomainDelegationFromVanityResponse;
import com.dnsimple.exception.DnsimpleException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrarDelegationTest extends DnsimpleTestBase {
  @Test
  public void testGetDomainDelegation() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectGet("/v2/1010/registrar/domains/example.com/delegation");
    server.stubFixtureAt("getDomainDelegation/success.http");
    Client client = new Client();

    GetDomainDelegationResponse response = client.registrar.getDomainDelegation(accountId, domainId);
    List<String> delegatedTo = response.getData();

    assertEquals(4, delegatedTo.size());
    assertEquals("ns1.dnsimple.com", delegatedTo.get(0));
    assertEquals("ns2.dnsimple.com", delegatedTo.get(1));
    assertEquals("ns3.dnsimple.com", delegatedTo.get(2));
    assertEquals("ns4.dnsimple.com", delegatedTo.get(3));
  }

  @Test
  public void testChangeDomainDelegation() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";
    List<String> nameServerNames = new ArrayList<>();
    nameServerNames.add("ns1.example.com");

    server.expectPut("/v2/1010/registrar/domains/example.com/delegation");
    server.expectJsonPayload(nameServerNames);
    server.stubFixtureAt("changeDomainDelegation/success.http");
    Client client = new Client();

    ChangeDomainDelegationResponse response = client.registrar.changeDomainDelegation(accountId, domainId, nameServerNames);
    List<String> delegatedTo = response.getData();
    assertEquals("ns1.dnsimple.com", delegatedTo.get(0));
    assertEquals("ns2.dnsimple.com", delegatedTo.get(1));
    assertEquals("ns3.dnsimple.com", delegatedTo.get(2));
    assertEquals("ns4.dnsimple.com", delegatedTo.get(3));
  }

  @Test
  public void testChangeDomainDelegationToVanity() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";
    List<String> nameServerNames = new ArrayList<String>();
    nameServerNames.add("ns1.example.com");
    nameServerNames.add("ns2.example.com");

    server.expectPut("/v2/1010/registrar/domains/example.com/delegation/vanity");
    server.expectJsonPayload(nameServerNames);
    server.stubFixtureAt("changeDomainDelegationToVanity/success.http");
    Client client = new Client();

    ChangeDomainDelegationToVanityResponse response = client.registrar.changeDomainDelegationToVanity(accountId, domainId, nameServerNames);
    List<NameServer> delegatedTo = response.getData();
    assertEquals(2, delegatedTo.size());

    NameServer nameServer = delegatedTo.get(0);
    assertEquals("ns1.example.com", nameServer.getName());
    assertEquals("127.0.0.1", nameServer.getIpv4());
    assertEquals("::1", nameServer.getIpv6());
    assertEquals("2016-07-11T09:40:19Z", nameServer.getCreatedAt());
    assertEquals("2016-07-11T09:40:19Z", nameServer.getUpdatedAt());
  }

  @Test
  public void testChangeDomainDelegationFromVanity() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    server.expectDelete("/v2/1010/registrar/domains/example.com/delegation/vanity");
    server.stubFixtureAt("changeDomainDelegationFromVanity/success.http");
    Client client = new Client();

    ChangeDomainDelegationFromVanityResponse response = client.registrar.changeDomainDelegationFromVanity(accountId, domainId);
    assertEquals(null, response.getData());
  }
}

package com.dnsimple;

import com.dnsimple.request.Filter;

import com.dnsimple.response.GetDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

public class RegistrarDelegationTest extends DnsimpleTestBase {
  @Test
  public void testGetDomainDelegation() throws DnsimpleException, IOException {
    String accountId = "1010";
    String domainId = "example.com";

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/delegation", HttpMethods.GET, null, resource("getDomainDelegation/success.http"));

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
    List<String> nameServerNames = new ArrayList<String>();
    nameServerNames.add("ns1.example.com");

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/registrar/domains/example.com/delegation", HttpMethods.PUT, nameServerNames, resource("changeDomainDelegation/success.http"));

    ChangeDomainDelegationResponse response = client.registrar.changeDomainDelegation(accountId, domainId, nameServerNames);
    List<String> delegatedTo = response.getData();
    assertEquals("ns1.dnsimple.com", delegatedTo.get(0));
    assertEquals("ns2.dnsimple.com", delegatedTo.get(1));
    assertEquals("ns3.dnsimple.com", delegatedTo.get(2));
    assertEquals("ns4.dnsimple.com", delegatedTo.get(3));
  }
}

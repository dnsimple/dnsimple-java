package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DomainsTest extends DnsimpleTestBase {

  @Test
  public void testListDomains() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDomains/success.http"));
    String accountId = "1";
    ListDomainsResponse response = client.domains.listDomains(accountId);
    List<Domain> domains = response.getData();
    assertEquals(2, domains.size());
    assertEquals(1, domains.get(0).getId());
  }
}

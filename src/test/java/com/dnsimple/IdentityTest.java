package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdentityTest extends DnsimpleTestBase {
  @Test
  public void testWhoami() throws DnsimpleException, IOException {
    Client client = mockClient(resource("whoami/success_account.http"));
    DnsimpleResponse response = client.identity.whoami();
    Account account = response.getData().getAccount();
    assertEquals(1, account.getId());
    assertEquals("example-account@example.com", account.getEmail());
  }
}

package com.dnsimple;

import com.dnsimple.response.WhoamiResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.google.api.client.util.Data;

public class IdentityTest extends DnsimpleTestBase {
  @Test
  public void testWhoamiWithAccount() throws DnsimpleException, IOException {
    Client client = mockClient(resource("whoami/success_account.http"));
    WhoamiResponse response = client.identity.whoami();
    Account account = response.getData().getAccount();
    assertEquals(1, account.getId().intValue());
    assertEquals("example-account@example.com", account.getEmail());
    assert(Data.isNull(response.getData().getUser()));
  }

  @Test
  public void testWhoamiWithUser() throws DnsimpleException, IOException {
    Client client = mockClient(resource("whoami/success_user.http"));
    WhoamiResponse response = client.identity.whoami();
    User user = response.getData().getUser();
    assertEquals(1, user.getId().intValue());
    assertEquals("example-user@example.com", user.getEmail());
    assert(Data.isNull(response.getData().getAccount()));
  }
}

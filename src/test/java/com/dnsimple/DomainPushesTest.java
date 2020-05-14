package com.dnsimple;

import com.dnsimple.data.Push;
import com.dnsimple.response.InitiatePushResponse;
import com.dnsimple.response.ListPushesResponse;
import com.dnsimple.response.AcceptPushResponse;
import com.dnsimple.response.RejectPushResponse;
import com.dnsimple.exception.DnsimpleException;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class DomainPushesTest extends DnsimpleTestBase {
  @Test
  public void testInitiatePushProducesPush() throws DnsimpleException, IOException {
    server.stubFixtureAt("initiatePush/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("new_account_email", "jim@example.com");

    InitiatePushResponse response = client.domains.initiatePush(accountId, domainId, attributes);
    Push push = response.getData();
    assertEquals(1, push.getId().intValue());
    assertEquals(100, push.getDomainId().intValue());
    assertTrue(Data.isNull(push.getContactId()));
    assertEquals("2016-08-11T10:16:03Z", push.getCreatedAt());
    assertEquals("2016-08-11T10:16:03Z", push.getUpdatedAt());
    assertTrue(Data.isNull(push.getAcceptedAt()));
  }

  @Test
  public void testListPushesProducesPushList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listPushes/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    ListPushesResponse response = client.domains.listPushes(accountId, domainId);

    List<Push> pushes = response.getData();
    assertEquals(2, pushes.size());
    assertEquals(1, pushes.get(0).getId().intValue());
  }

  @Test
  public void testAcceptPush() throws DnsimpleException, IOException {
    server.stubFixtureAt("acceptPush/success.http");
    Client client = new Client();

    String accountId = "1010";
    String pushId = "200";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("contact_id", 1);

    AcceptPushResponse response = client.domains.acceptPush(accountId, pushId, attributes);
    assertEquals(null, response.getData());
  }

  @Test
  public void testRejectPush() throws DnsimpleException, IOException {
    server.stubFixtureAt("rejectPush/success.http");
    Client client = new Client();

    String accountId = "1010";
    String pushId = "200";

    RejectPushResponse response = client.domains.rejectPush(accountId, pushId);
    assertEquals(null, response.getData());
  }
}

package com.dnsimple;

import com.dnsimple.request.Filter;

import com.dnsimple.response.InitiatePushResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Data;

public class DomainPushesTest extends DnsimpleTestBase {
  @Test
  public void testInitiatePushProducesPush() throws DnsimpleException, IOException {
    Client client = mockClient(resource("initiatePush/success.http"));

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("new_account_email", "jim@example.com");

    InitiatePushResponse response = client.domains.initiatePush(accountId, domainId, attributes);
    Push push = response.getData();
    assertEquals(1, push.getId().intValue());
    assertEquals(100, push.getDomainId().intValue());
    assertTrue(Data.isNull(push.getContactId()));
    assertEquals("2016-08-11T10:16:03.340Z", push.getCreatedAt());
    assertEquals("2016-08-11T10:16:03.340Z", push.getUpdatedAt());
    assertTrue(Data.isNull(push.getAcceptedAt()));
  }
}

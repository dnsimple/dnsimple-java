package com.dnsimple;

import com.dnsimple.data.Webhook;
import com.dnsimple.response.ListWebhooksResponse;
import com.dnsimple.response.GetWebhookResponse;
import com.dnsimple.response.CreateWebhookResponse;
import com.dnsimple.response.DeleteWebhookResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class WebhooksTest extends DnsimpleTestBase {

  @Test
  public void testListWebhooksSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    server.expectGet("/v2/1010/webhooks?foo=bar");
    Client client = new Client();

    String accountId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.webhooks.listWebhooks(accountId, options);
  }

  @Test
  public void testListWebhooksProducesWebhookList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listWebhooks/success.http");
    Client client = new Client();

    String accountId = "1010";

    ListWebhooksResponse response = client.webhooks.listWebhooks(accountId);

    List<Webhook> webhooks = response.getData();
    assertEquals(2, webhooks.size());
    assertEquals(1, webhooks.get(0).getId().intValue());
  }

  @Test
  public void testGetWebhook() throws DnsimpleException, IOException {
    server.expectGet("/v2/1010/webhooks/1");
    server.stubFixtureAt("getWebhook/success.http");
    Client client = new Client();

    String accountId = "1010";
    String webhookId = "1";

    GetWebhookResponse response = client.webhooks.getWebhook(accountId, webhookId);

    Webhook webhook = response.getData();
    assertEquals(1, webhook.getId().intValue());
    assertEquals("https://webhook.test", webhook.getUrl());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetWebhookWhenNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-webhook.http");
    Client client = new Client();

    String accountId = "1010";
    String webhookId = "1";

    client.webhooks.getWebhook(accountId, webhookId);
  }

  @Test
  public void testCreateWebhook() throws DnsimpleException, IOException {
    String accountId = "1010";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("url", "https://webhook.test");

    server.expectPost("/v2/1010/webhooks");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("createWebhook/created.http");
    Client client = new Client();

    CreateWebhookResponse response = client.webhooks.createWebhook(accountId, attributes);
    Webhook webhook = response.getData();
    assertEquals(1, webhook.getId().intValue());
    assertEquals("https://webhook.test", webhook.getUrl());
  }

  @Test
  public void testDeleteWebhook() throws DnsimpleException, IOException {
    String accountId = "1010";
    String webhookId = "1";

    server.expectDelete("/v2/1010/webhooks/1");
    server.stubFixtureAt("deleteWebhook/success.http");
    Client client = new Client();

    DeleteWebhookResponse response = client.webhooks.deleteWebhook(accountId, webhookId);
    assertEquals(null, response.getData());
  }
}

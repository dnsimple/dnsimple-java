package com.dnsimple.endpoints.http;

import com.dnsimple.Webhooks;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.CreateWebhookResponse;
import com.dnsimple.response.DeleteWebhookResponse;
import com.dnsimple.response.GetWebhookResponse;
import com.dnsimple.response.ListWebhooksResponse;
import java.io.IOException;
import java.util.Map;

public class WebhooksEndpoint implements Webhooks {
  private HttpEndpointClient client;

  public WebhooksEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListWebhooksResponse listWebhooks(String accountId) throws DnsimpleException, IOException {
    return listWebhooks(accountId, null);
  }

  public ListWebhooksResponse listWebhooks(String accountId, Map<String, Object> options) throws DnsimpleException, IOException {
    return (ListWebhooksResponse) client.get(accountId + "/webhooks", options, ListWebhooksResponse.class);
  }

  public GetWebhookResponse getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    return (GetWebhookResponse) client.get(accountId + "/webhooks/" + webhookId, null, GetWebhookResponse.class);
  }

  public CreateWebhookResponse createWebhook(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException {
    return (CreateWebhookResponse) client.post(accountId + "/webhooks", attributes, null, CreateWebhookResponse.class);
  }

  public DeleteWebhookResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    return (DeleteWebhookResponse) client.delete(accountId + "/webhooks/" + webhookId, null, DeleteWebhookResponse.class);
  }
}

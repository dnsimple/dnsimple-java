package com.dnsimple.endpoints;

import com.dnsimple.Webhooks;
import com.dnsimple.response.ListWebhooksResponse;
import com.dnsimple.response.GetWebhookResponse;
import com.dnsimple.response.CreateWebhookResponse;
import com.dnsimple.response.DeleteWebhookResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WebhooksEndpoint implements Webhooks {
  private HttpEndpointClient client;

  public WebhooksEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListWebhooksResponse listWebhooks(String accountId) throws DnsimpleException, IOException {
    return listWebhooks(accountId, null);
  }

  public ListWebhooksResponse listWebhooks(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/webhooks", options);
    return (ListWebhooksResponse)client.parseResponse(response, ListWebhooksResponse.class);
  }

  public GetWebhookResponse getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/webhooks/" + webhookId);
    return (GetWebhookResponse)client.parseResponse(response, GetWebhookResponse.class);
  }

  public CreateWebhookResponse createWebhook(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/webhooks", attributes);
    return (CreateWebhookResponse)client.parseResponse(response, CreateWebhookResponse.class);
  }

  public DeleteWebhookResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/webhooks/" + webhookId);
    return (DeleteWebhookResponse)client.parseResponse(response, DeleteWebhookResponse.class);
  }
}

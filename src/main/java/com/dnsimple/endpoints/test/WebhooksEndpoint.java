package com.dnsimple.endpoints.test;

import com.dnsimple.Webhooks;
import com.dnsimple.response.ListWebhooksResponse;
import com.dnsimple.response.GetWebhookResponse;
import com.dnsimple.response.CreateWebhookResponse;
import com.dnsimple.response.DeleteWebhookResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

public class WebhooksEndpoint implements Webhooks {

  public ListWebhooksResponse listWebhooks(String accountId) throws DnsimpleException, IOException {
    return new ListWebhooksResponse();
  }

  public ListWebhooksResponse listWebhooks(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListWebhooksResponse();
  }

  public GetWebhookResponse getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    return new GetWebhookResponse();
  }

  public CreateWebhookResponse createWebhook(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateWebhookResponse();
  }

  public DeleteWebhookResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    return new DeleteWebhookResponse();
  }

}


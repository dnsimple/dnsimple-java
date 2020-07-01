package com.dnsimple.endpoints.http;

import com.dnsimple.Webhooks;
import com.dnsimple.data.Webhook;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public class WebhooksEndpoint implements Webhooks {
    private final HttpEndpointClient client;

    public WebhooksEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Webhook> listWebhooks(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listWebhooks(accountId, null);
    }

    public ListResponse<Webhook> listWebhooks(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.getList(accountId + "/webhooks", options, Webhook.class);
    }

    public SimpleResponse<Webhook> getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/webhooks/" + webhookId, null, Webhook.class);
    }

    public SimpleResponse<Webhook> createWebhook(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/webhooks", attributes, null, Webhook.class);
    }

    public EmptyResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/webhooks/" + webhookId, null);
    }
}

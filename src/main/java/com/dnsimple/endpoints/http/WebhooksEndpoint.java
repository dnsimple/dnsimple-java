package com.dnsimple.endpoints.http;

import com.dnsimple.Webhooks;
import com.dnsimple.data.Webhook;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.dnsimple.endpoints.http.java11.HttpMethod.GET;
import static java.util.Collections.emptyMap;

public class WebhooksEndpoint implements Webhooks {
    private final HttpEndpointClient client;

    public WebhooksEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Webhook> listWebhooks(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listWebhooks(accountId, null);
    }

    public ListResponse<Webhook> listWebhooks(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.list(GET, accountId + "/webhooks", options, emptyMap(), Webhook.class);
    }

    public SimpleResponse<Webhook> getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/webhooks/" + webhookId, null, Collections.emptyMap(), Webhook.class);
    }

    public SimpleResponse<Webhook> createWebhook(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/webhooks", attributes, null, Webhook.class);
    }

    public EmptyResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/webhooks/" + webhookId, null);
    }
}

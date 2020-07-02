package com.dnsimple.endpoints.http;

import com.dnsimple.Webhooks;
import com.dnsimple.data.Webhook;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.emptyMap;

public class WebhooksEndpoint implements Webhooks {
    private final HttpEndpointClient client;

    public WebhooksEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Webhook> listWebhooks(String accountId) {
        return client.list(GET, accountId + "/webhooks", emptyMap(), null, Webhook.class);
    }

    public ListResponse<Webhook> listWebhooks(String accountId, Map<String, Object> options) {
        return client.list(GET, accountId + "/webhooks", options, null, Webhook.class);
    }

    public SimpleResponse<Webhook> getWebhook(String accountId, String webhookId) {
        return client.simple(GET, accountId + "/webhooks/" + webhookId, emptyMap(), null, Webhook.class);
    }

    public SimpleResponse<Webhook> createWebhook(String accountId, Map<String, Object> attributes) {
        return client.simple(POST, accountId + "/webhooks", emptyMap(), attributes, Webhook.class);
    }

    public EmptyResponse deleteWebhook(String accountId, String webhookId) {
        return client.empty(DELETE, accountId + "/webhooks/" + webhookId, emptyMap(), null);
    }
}

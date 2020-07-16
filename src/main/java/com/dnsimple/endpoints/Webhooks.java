package com.dnsimple.endpoints;

import com.dnsimple.data.Webhook;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;

/**
 * Provides access to the DNSimple Webhooks API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/webhooks">https://developer.dnsimple.com/v2/webhooks</a>
 */
public class Webhooks {
    private final HttpEndpointClient client;

    public Webhooks(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the webhooks in the account.
     *
     * @param account The account ID
     * @return The list webhooks response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
     */
    public ListResponse<Webhook> listWebhooks(Number account) {
        return client.list(GET, account + "/webhooks", ListOptions.empty(), null, Webhook.class);
    }

    /**
     * Lists the webhooks in the account.
     *
     * @param account The account ID
     * @param options The options for the list request
     * @return The list webhooks response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
     */
    public ListResponse<Webhook> listWebhooks(Number account, ListOptions options) {
        return client.list(GET, account + "/webhooks", options, null, Webhook.class);
    }

    /**
     * Get a specific webhook associated to an account using the webhook's ID.
     *
     * @param account   The account ID
     * @param webhookId The webhook ID
     * @return The get webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#get">https://developer.dnsimple.com/v2/webhooks/#get</a>
     */
    public SimpleResponse<Webhook> getWebhook(Number account, Number webhookId) {
        return client.simple(GET, account + "/webhooks/" + webhookId, ListOptions.empty(), null, Webhook.class);
    }

    /**
     * Create a webhook in the account.
     *
     * @param account    The account ID
     * @param attributes A Map of attributes for constructing the webhook
     * @return The create webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#create">https://developer.dnsimple.com/v2/webhooks/#create</a>
     */
    public SimpleResponse<Webhook> createWebhook(Number account, Map<String, Object> attributes) {
        return client.simple(POST, account + "/webhooks", ListOptions.empty(), attributes, Webhook.class);
    }

    /**
     * Delete a webhook from the account.
     *
     * @param account   The account ID
     * @param webhookId The webhook ID
     * @return The delete webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#delete">https://developer.dnsimple.com/v2/webhooks/#delete</a>
     */
    public EmptyResponse deleteWebhook(Number account, Number webhookId) {
        return client.empty(DELETE, account + "/webhooks/" + webhookId, ListOptions.empty(), null);
    }
}

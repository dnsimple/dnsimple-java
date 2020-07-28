package com.dnsimple.endpoints;

import com.dnsimple.data.Webhook;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.http.HttpMethod.*;
import static java.util.Collections.singletonMap;

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
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#listWebhooks">https://developer.dnsimple.com/v2/webhooks/#listWebhooks</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#listWebhooks">https://developer.dnsimple.com/v2/webhooks/#listWebhooks</a>
     */
    public ListResponse<Webhook> listWebhooks(Number account, ListOptions options) {
        return client.list(GET, account + "/webhooks", options, null, Webhook.class);
    }

    /**
     * Create a webhook in the account.
     *
     * @param account The account ID
     * @param url     The url of the webhook
     * @return The create webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#createWebhook">https://developer.dnsimple.com/v2/webhooks/#createWebhook</a>
     */
    public SimpleResponse<Webhook> createWebhook(Number account, String url) {
        return client.simple(POST, account + "/webhooks", ListOptions.empty(), singletonMap("url", url), Webhook.class);
    }

    /**
     * Get a specific webhook associated to an account using the webhook's ID.
     *
     * @param account   The account ID
     * @param webhookId The webhook ID
     * @return The get webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#getWebhook">https://developer.dnsimple.com/v2/webhooks/#getWebhook</a>
     */
    public SimpleResponse<Webhook> getWebhook(Number account, Number webhookId) {
        return client.simple(GET, account + "/webhooks/" + webhookId, ListOptions.empty(), null, Webhook.class);
    }

    /**
     * Delete a webhook from the account.
     *
     * @param account   The account ID
     * @param webhookId The webhook ID
     * @return The delete webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#deleteWebhook">https://developer.dnsimple.com/v2/webhooks/#deleteWebhook</a>
     */
    public EmptyResponse deleteWebhook(Number account, Number webhookId) {
        return client.empty(DELETE, account + "/webhooks/" + webhookId, ListOptions.empty(), null);
    }
}

package com.dnsimple;

import com.dnsimple.data.Webhook;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

public interface Webhooks {
    /**
     * Lists the webhooks in the account.
     *
     * @param accountId The account ID
     * @return The list webhooks response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
     */
    ListResponse<Webhook> listWebhooks(String accountId);

    /**
     * Lists the webhooks in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the webhooks API
     * @return The list webhooks response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
     */
    ListResponse<Webhook> listWebhooks(String accountId, Map<String, Object> options);

    /**
     * Get a specific webhook associated to an account using the webhook's ID.
     *
     * @param accountId The account ID
     * @param webhookId The webhook ID
     * @return The get webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#get">https://developer.dnsimple.com/v2/webhooks/#get</a>
     */
    SimpleResponse<Webhook> getWebhook(String accountId, String webhookId);

    /**
     * Create a webhook in the account.
     *
     * @param accountId  The account ID
     * @param attributes A Map of attributes for constructing the webhook
     * @return The create webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#create">https://developer.dnsimple.com/v2/webhooks/#create</a>
     */
    SimpleResponse<Webhook> createWebhook(String accountId, Map<String, Object> attributes);

    /**
     * Delete a webhook from the account.
     *
     * @param accountId The account ID
     * @param webhookId The webhook ID
     * @return The delete webhook response
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#delete">https://developer.dnsimple.com/v2/webhooks/#delete</a>
     */
    EmptyResponse deleteWebhook(String accountId, String webhookId);
}

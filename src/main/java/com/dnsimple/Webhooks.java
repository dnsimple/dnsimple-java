package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.CreateWebhookResponse;
import com.dnsimple.response.DeleteWebhookResponse;
import com.dnsimple.response.GetWebhookResponse;
import com.dnsimple.response.ListWebhooksResponse;

import java.io.IOException;
import java.util.Map;

public interface Webhooks {
    /**
     * Lists the webhooks in the account.
     *
     * @param accountId The account ID
     * @return The list webhooks response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
     */
    public ListWebhooksResponse listWebhooks(String accountId) throws DnsimpleException, IOException;

    /**
     * Lists the webhooks in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the webhooks API
     * @return The list webhooks response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
     */
    public ListWebhooksResponse listWebhooks(String accountId, Map<String, Object> options) throws DnsimpleException, IOException;

    /**
     * Get a specific webhook associated to an account using the webhook's ID.
     *
     * @param accountId The account ID
     * @param webhookId The webhook ID
     * @return The get webhook response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#get">https://developer.dnsimple.com/v2/webhooks/#get</a>
     */
    public GetWebhookResponse getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException;

    /**
     * Create a webhook in the account.
     *
     * @param accountId  The account ID
     * @param attributes A Map of attributes for constructing the webhook
     * @return The create webhook response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#create">https://developer.dnsimple.com/v2/webhooks/#create</a>
     */
    public CreateWebhookResponse createWebhook(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException;

    /**
     * Delete a webhook from the account.
     *
     * @param accountId The account ID
     * @param webhookId The webhook ID
     * @return The delete webhook response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/webhooks/#delete">https://developer.dnsimple.com/v2/webhooks/#delete</a>
     */
    public DeleteWebhookResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException;
}

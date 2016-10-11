package com.dnsimple;

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

public interface Webhooks {

  /**
   * Lists the webhooks in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
   *
   * @param accountId The account ID
   * @return The list webhooks response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListWebhooksResponse listWebhooks(String accountId) throws DnsimpleException, IOException;

  /**
   * Lists the webhooks in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/webhooks/#list">https://developer.dnsimple.com/v2/webhooks/#list</a>
   *
   * @param accountId The account ID
   * @param options A Map of options to pass to the webhooks API
   * @return The list webhooks response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListWebhooksResponse listWebhooks(String accountId, Map<String,Object> options) throws DnsimpleException, IOException;

  /**
   * Get a specific webhook associated to an account using the webhook's ID.
   *
   * @see <a href="https://developer.dnsimple.com/v2/webhooks/#get">https://developer.dnsimple.com/v2/webhooks/#get</a>
   *
   * @param accountId The account ID
   * @param webhookId The webhook ID
   * @return The get webhook response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetWebhookResponse getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException;

  /**
   * Create a webhook in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/webhooks/#create">https://developer.dnsimple.com/v2/webhooks/#create</a>
   *
   * @param accountId The account ID
   * @param attributes A Map of attributes for constructing the webhook
   * @return The create webhook response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateWebhookResponse createWebhook(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Delete a webhook from the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/webhooks/#delete">https://developer.dnsimple.com/v2/webhooks/#delete</a>
   *
   * @param accountId The account ID
   * @param webhookId The webhook ID
   * @return The delete webhook response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteWebhookResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException;

}

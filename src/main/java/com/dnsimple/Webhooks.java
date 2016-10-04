package com.dnsimple;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.dnsimple.response.ListWebhooksResponse;
import com.dnsimple.response.GetWebhookResponse;
import com.dnsimple.response.CreateWebhookResponse;
import com.dnsimple.response.DeleteWebhookResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple Webhooks API.
 *
 * @see https://developer.dnsimple.com/v2/webhooks
 */
public class Webhooks {
  private Client client;

  protected Webhooks(Client client) {
    this.client = client;
  }

  /**
   * Lists the webhooks in the account.
   *
   * @see https://developer.dnsimple.com/v2/webhooks/#list
   *
   * @param accountId The account ID
   * @return The list webhooks response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListWebhooksResponse listWebhooks(String accountId) throws DnsimpleException, IOException {
    return listWebhooks(accountId, null);
  }

  /**
   * Lists the webhooks in the account.
   *
   * @see https://developer.dnsimple.com/v2/webhooks/#list
   *
   * @param accountId The account ID
   * @param options A Map of options to pass to the webhooks API
   * @return The list webhooks response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListWebhooksResponse listWebhooks(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/webhooks", options);
    return (ListWebhooksResponse)client.parseResponse(response, ListWebhooksResponse.class);
  }

  /**
   * Get a specific webhook associated to an account using the webhook's ID.
   *
   * @see https://developer.dnsimple.com/v2/webhooks/#get
   *
   * @param accountId The account ID
   * @param webhookId The webhook ID
   * @return The get webhook response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetWebhookResponse getWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/webhooks/" + webhookId);
    return (GetWebhookResponse)client.parseResponse(response, GetWebhookResponse.class);
  }

  /**
   * Create a webhook in the account.
   *
   * @see https://developer.dnsimple.com/v2/webhooks/#create
   *
   * @param accountId The account ID
   * @param attributes A Map of attributes for constructing the webhook
   * @return The create webhook response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateWebhookResponse createWebhook(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/webhooks", attributes);
    return (CreateWebhookResponse)client.parseResponse(response, CreateWebhookResponse.class);
  }

  /**
   * Delete a webhook from the account.
   *
   * @see https://developer.dnsimple.com/v2/webhooks/#delete
   *
   * @param accountId The account ID
   * @param webhookId The webhook ID
   * @return The delete webhook response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteWebhookResponse deleteWebhook(String accountId, String webhookId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/webhooks/" + webhookId);
    return (DeleteWebhookResponse)client.parseResponse(response, DeleteWebhookResponse.class);
  }
}

package com.dnsimple;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.GetDomainResponse;
import com.dnsimple.response.CreateDomainResponse;
import com.dnsimple.response.DeleteDomainResponse;
import com.dnsimple.response.ResetDomainTokenResponse;

import com.dnsimple.response.ListEmailForwardsResponse;
import com.dnsimple.response.GetEmailForwardResponse;
import com.dnsimple.response.CreateEmailForwardResponse;
import com.dnsimple.response.DeleteEmailForwardResponse;

import com.dnsimple.response.InitiatePushResponse;
import com.dnsimple.response.ListPushesResponse;
import com.dnsimple.response.AcceptPushResponse;
import com.dnsimple.response.RejectPushResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple Domains API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains">https://developer.dnsimple.com/v2/domains</a>
 */
public class Domains {
  private Client client;

  protected Domains(Client client) {
    this.client = client;
  }

  // Domains

  /**
   * Lists the domains in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/#list">https://developer.dnsimple.com/v2/domains/#list</a>
   *
   * @param accountId The account ID
   * @return The list domains response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListDomainsResponse listDomains(String accountId) throws DnsimpleException, IOException {
    return listDomains(accountId, null);
  }

  /**
   * Lists the domains in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/#list">https://developer.dnsimple.com/v2/domains/#list</a>
   *
   * @param accountId The account ID
   * @param options A Map of options to pass to the domains API
   * @return The list domains response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListDomainsResponse listDomains(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains", options);
    return (ListDomainsResponse)client.parseResponse(response, ListDomainsResponse.class);
  }

  /**
   * Get a specific domain associated to an account using the domain's name or ID.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/#get">https://developer.dnsimple.com/v2/domains/#get</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The get domain response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetDomainResponse getDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId);
    return (GetDomainResponse)client.parseResponse(response, GetDomainResponse.class);
  }

  /**
   * Create a domain in an account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/#create">https://developer.dnsimple.com/v2/domains/#create</a>
   *
   * @param accountId The account ID
   * @param attributes A Map of attributes for constructing the domain
   * @return The create domain response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateDomainResponse createDomain(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains", attributes);
    return (CreateDomainResponse)client.parseResponse(response, CreateDomainResponse.class);
  }

  /**
   * Delete a domain from an account.
   *
   * WARNING: this cannot be undone.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/#delete">https://developer.dnsimple.com/v2/domains/#delete</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @return The delete domain response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteDomainResponse deleteDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId);
    return (DeleteDomainResponse)client.parseResponse(response, DeleteDomainResponse.class);
  }

  /**
   * Resets the domain token.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/#reset-token">https://developer.dnsimple.com/v2/domains/#reset-token</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @return The reset token domain response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ResetDomainTokenResponse resetDomainToken(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId);
    return (ResetDomainTokenResponse)client.parseResponse(response, ResetDomainTokenResponse.class);
  }

  // Email Forwards

  /**
   * List email forwards under a given domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#list">https://developer.dnsimple.com/v2/domains/email-forwards/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @return The list email forwards response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListEmailForwardsResponse listEmailForwards(String accountId, String domainId) throws DnsimpleException, IOException {
    return listEmailForwards(accountId, domainId, null);
  }

  /**
   * List email forwards under a given domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#list">https://developer.dnsimple.com/v2/domains/email-forwards/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @param options A Map of options to send to the API
   * @return The list email forwards response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListEmailForwardsResponse listEmailForwards(String accountId, String domainId, HashMap<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/email_forwards", options);
    return (ListEmailForwardsResponse)client.parseResponse(response, ListEmailForwardsResponse.class);
  }

  /**
   * Get a specific email forward associated to a domain using the email forward's ID.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#get">https://developer.dnsimple.com/v2/domains/email-forwards/#get</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param emailForwardId The email forward ID
   * @return The get email forward response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetEmailForwardResponse getEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException {
    try {
      HttpResponse response = client.get(accountId + "/domains/" + domainId + "/email_forwards/" + emailForwardId);
      return (GetEmailForwardResponse)client.parseResponse(response, GetEmailForwardResponse.class);
    } catch(HttpResponseException e) {
      throw DnsimpleException.transformException(e);
    }
  }

  /**
   * Create an email forward for a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#create">https://developer.dnsimple.com/v2/domains/email-forwards/#create</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param attributes A Map of attributes for constructing the email forward
   * @return The create email forward response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateEmailForwardResponse createEmailForward(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/email_forwards", attributes);
    return (CreateEmailForwardResponse)client.parseResponse(response, CreateEmailForwardResponse.class);
  }

  /**
   * Delete an email forward from a domain.
   *
   * WARNING: this cannot be undone.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#delete">https://developer.dnsimple.com/v2/domains/email-forwards/#delete</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @param emailForwardId The email forward ID
   * @return The delete email forward response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteEmailForwardResponse deleteEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId + "/email_forwards/" + emailForwardId);
    return (DeleteEmailForwardResponse)client.parseResponse(response, DeleteEmailForwardResponse.class);
  }

  // Pushes

  /**
   * Initiate a push.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#initiate">https://developer.dnsimple.com/v2/domains/pushes/#initiate</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param attributes A Map of attributes for constructing the push
   * @return The initiate push response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public InitiatePushResponse initiatePush(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/pushes", attributes);
    return (InitiatePushResponse)client.parseResponse(response, InitiatePushResponse.class);
  }

  /**
   * List pushes under a given domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#list">https://developer.dnsimple.com/v2/domains/pushes/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @return The list pushes response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListPushesResponse listPushes(String accountId, String domainId) throws DnsimpleException, IOException {
    return listPushes(accountId, domainId, null);
  }

  /**
   * List pushes under a given domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#list">https://developer.dnsimple.com/v2/domains/pushes/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID or name
   * @param options A Map of options to send to the API
   * @return The list pushes response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListPushesResponse listPushes(String accountId, String domainId, HashMap<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/pushes", options);
    return (ListPushesResponse)client.parseResponse(response, ListPushesResponse.class);
  }

  /**
   * Accept a push.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#accept">https://developer.dnsimple.com/v2/domains/pushes/#accept</a>
   *
   * @param accountId The account ID
   * @param pushId The push ID
   * @param attributes A Map of attributes required when accepting the push
   * @return The accept push response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public AcceptPushResponse acceptPush(String accountId, String pushId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/pushes/" + pushId, attributes);
    return (AcceptPushResponse)client.parseResponse(response, AcceptPushResponse.class);
  }

  /**
   * Reject a push.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#reject">https://developer.dnsimple.com/v2/domains/pushes/#reject</a>
   *
   * @param accountId The account ID
   * @param pushId The push ID
   * @return The accept push response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public RejectPushResponse rejectPush(String accountId, String pushId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/pushes/" + pushId);
    return (RejectPushResponse)client.parseResponse(response, RejectPushResponse.class);
  }

}

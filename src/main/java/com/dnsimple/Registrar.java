package com.dnsimple;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.dnsimple.response.CheckDomainResponse;
import com.dnsimple.response.RegisterDomainResponse;
import com.dnsimple.response.RenewDomainResponse;
import com.dnsimple.response.TransferDomainResponse;
import com.dnsimple.response.TransferDomainOutResponse;
import com.dnsimple.response.EnableAutoRenewalResponse;
import com.dnsimple.response.DisableAutoRenewalResponse;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

/**
 * Provides access to the DNSimple Registrar API.
 *
 * @see https://developer.dnsimple.com/v2/registrar
 */
public class Registrar {
  private Client client;

  protected Registrar(Client client) {
    this.client = client;
  }

  /**
   * Checks whether a domain is available for registration.
   *
   * @see https://developer.dnsimple.com/v2/registrar/#check
   *
   * @param accountId The account ID
   * @param domainName The domain to check
   * @return The check domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public CheckDomainResponse checkDomain(String accountId, String domainName) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/registrar/domains/" + domainName + "/check");
    return (CheckDomainResponse)client.parseResponse(response, CheckDomainResponse.class);
  }

  /**
   * Registers a domain.
   *
   * @see https://developer.dnsimple.com/v2/registrar/#register
   * @param accountId The account ID
   * @param domainName The domain to register
   * @return The register domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public RegisterDomainResponse registerDomain(String accountId, String domainName, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainName + "/register", attributes);
    return (RegisterDomainResponse)client.parseResponse(response, RegisterDomainResponse.class);
  }

  /**
   * Renews a domain.
   *
   * @see https://developer.dnsimple.com/v2/registrar/#renew
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The renew domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public RenewDomainResponse renewDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/renewal", attributes);
    return (RenewDomainResponse)client.parseResponse(response, RenewDomainResponse.class);
  }

  /**
   * Starts the transfer of a domain to DNSimple.
   *
   * @see https://developer.dnsimple.com/v2/registrar/#transfer
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The transfer domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public TransferDomainResponse transferDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/transfer", attributes);
    return (TransferDomainResponse)client.parseResponse(response, TransferDomainResponse.class);
  }

  /**
   * Requests the transfer of a domain out of DNSimple.
   *
   * @see https://developer.dnsimple.com/v2/registrar/#transfer-out
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The transfer domain out response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public TransferDomainOutResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/transfer_out");
    return (TransferDomainOutResponse)client.parseResponse(response, TransferDomainOutResponse.class);
  }

  /**
   * Enable auto renewal for the domain in the account.
   *
   * @see https://developer.dnsimple.com/v2/registrar/auto-renewal/
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The enable auto renewal response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public EnableAutoRenewalResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.put(accountId + "/domains/" + domainId + "/auto_renewal");
    return (EnableAutoRenewalResponse)client.parseResponse(response, EnableAutoRenewalResponse.class);
  }

  /**
   * Disable auto renewal for the domain in the account.
   *
   * @see https://developer.dnsimple.com/v2/registrar/auto-renewal/
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The disable auto renewal response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public DisableAutoRenewalResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId + "/auto_renewal");
    return (DisableAutoRenewalResponse)client.parseResponse(response, DisableAutoRenewalResponse.class);
  }
}

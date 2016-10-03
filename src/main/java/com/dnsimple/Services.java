package com.dnsimple;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.dnsimple.response.ListServicesResponse;
import com.dnsimple.response.GetServiceResponse;
import com.dnsimple.response.AppliedServicesResponse;
import com.dnsimple.response.ApplyServiceResponse;
import com.dnsimple.response.UnapplyServiceResponse;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

/**
 * Provides access to the DNSimple one-click Services API.
 *
 * @see https://developer.dnsimple.com/v2/services
 */
public class Services {
  private Client client;

  protected Services(Client client) {
    this.client = client;
  }

  /**
   * Lists the available one-click services.
   *
   * @see https://developer.dnsimple.com/v2/services/#list
   * @return The list services response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListServicesResponse listServices() throws DnsimpleException, IOException {
    return listServices(new HashMap<String, Object>());
  }

  /**
   * Lists the available one-click services.
   *
   * @see https://developer.dnsimple.com/v2/services/#list
   * @return The list services response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListServicesResponse listServices(Map<String, Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get("services", options);
    return (ListServicesResponse)client.parseResponse(response, ListServicesResponse.class);
  }

  /**
   * Get a specific service by ID.
   *
   * @see https://developer.dnsimple.com/v2/services/#get
   * @param serviceId The service ID
   * @return The get service response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetServiceResponse getService(String serviceId) throws DnsimpleException, IOException {
    HttpResponse response = client.get("services/" + serviceId);
    return (GetServiceResponse)client.parseResponse(response, GetServiceResponse.class);
  }

  /**
   * Lists the one-click services applied to the domain.
   *
   * @see https://developer.dnsimple.com/v2/services/domains/#applied
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The applied services response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public AppliedServicesResponse appliedServices(String accountId, String domainId) throws DnsimpleException, IOException {
    return appliedServices(accountId, domainId, new HashMap<String, Object>());
  }

  /**
   * Lists the one-click services applied to the domain.
   *
   * @see https://developer.dnsimple.com/v2/services/domains/#applied
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param options Options passed to the DNSimple API
   * @return The applied services response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public AppliedServicesResponse appliedServices(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/services", options);
    return (AppliedServicesResponse)client.parseResponse(response, AppliedServicesResponse.class);
  }

  public ApplyServiceResponse applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/services/" + serviceId, settings);
    return (ApplyServiceResponse)client.parseResponse(response, ApplyServiceResponse.class);
  }

  public UnapplyServiceResponse unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId + "/services/" + serviceId);
    return (UnapplyServiceResponse)client.parseResponse(response, UnapplyServiceResponse.class);
  }
}

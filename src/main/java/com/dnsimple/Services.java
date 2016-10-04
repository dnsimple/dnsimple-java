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
 * @see <a href="https://developer.dnsimple.com/v2/services">https://developer.dnsimple.com/v2/services</a>
 */
public class Services {
  private Client client;

  protected Services(Client client) {
    this.client = client;
  }

  /**
   * Lists the available one-click services.
   *
   * @see <a href="https://developer.dnsimple.com/v2/services/#list">https://developer.dnsimple.com/v2/services/#list</a>
   *
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
   * @see <a href="https://developer.dnsimple.com/v2/services/#list">https://developer.dnsimple.com/v2/services/#list</a>
   *
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
   * @see <a href="https://developer.dnsimple.com/v2/services/#get">https://developer.dnsimple.com/v2/services/#get</a>
   *
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
   * @see <a href="https://developer.dnsimple.com/v2/services/domains/#applied">https://developer.dnsimple.com/v2/services/domains/#applied</a>
   *
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
   * @see <a href="https://developer.dnsimple.com/v2/services/domains/#applied">https://developer.dnsimple.com/v2/services/domains/#applied</a>
   *
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

  /**
   * Apply the given one-click service to the given domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/services/domains/#apply">https://developer.dnsimple.com/v2/services/domains/#apply</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param serviceId The service ID to apply
   * @param settings A Map of settings for the service
   * @return The apply service response
   * @throws DNsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ApplyServiceResponse applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/services/" + serviceId, settings);
    return (ApplyServiceResponse)client.parseResponse(response, ApplyServiceResponse.class);
  }

  /**
   * Unapply the given one-click service  the given domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/services/domains/#apply">https://developer.dnsimple.com/v2/services/domains/#apply</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param serviceId The service ID to unapply
   * @return The unapply service response
   * @throws DNsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public UnapplyServiceResponse unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId + "/services/" + serviceId);
    return (UnapplyServiceResponse)client.parseResponse(response, UnapplyServiceResponse.class);
  }
}

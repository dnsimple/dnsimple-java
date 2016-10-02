package com.dnsimple;

import java.io.IOException;
import java.util.List;

import com.dnsimple.response.ListServicesResponse;
import com.dnsimple.response.GetServiceResponse;

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
    HttpResponse response = client.get("/services");
    return (ListServicesResponse)client.parseResponse(response, ListServicesResponse.class);
  }

  public GetServiceResponse getService(String serviceId) throws DnsimpleException, IOException {
    HttpResponse response = client.get("/services/" + serviceId);
    return (GetServiceResponse)client.parseResponse(response, GetServiceResponse.class);
  }
}

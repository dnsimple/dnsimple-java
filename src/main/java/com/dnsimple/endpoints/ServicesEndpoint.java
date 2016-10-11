package com.dnsimple.endpoints;

import com.dnsimple.Services;
import com.dnsimple.response.ListServicesResponse;
import com.dnsimple.response.GetServiceResponse;
import com.dnsimple.response.AppliedServicesResponse;
import com.dnsimple.response.ApplyServiceResponse;
import com.dnsimple.response.UnapplyServiceResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class ServicesEndpoint implements Services {
  private HttpEndpointClient client;

  public ServicesEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListServicesResponse listServices() throws DnsimpleException, IOException {
    return listServices(new HashMap<String, Object>());
  }

  public ListServicesResponse listServices(Map<String, Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get("services", options);
    return (ListServicesResponse)client.parseResponse(response, ListServicesResponse.class);
  }

  public GetServiceResponse getService(String serviceId) throws DnsimpleException, IOException {
    HttpResponse response = client.get("services/" + serviceId);
    return (GetServiceResponse)client.parseResponse(response, GetServiceResponse.class);
  }

  public AppliedServicesResponse appliedServices(String accountId, String domainId) throws DnsimpleException, IOException {
    return appliedServices(accountId, domainId, new HashMap<String, Object>());
  }

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

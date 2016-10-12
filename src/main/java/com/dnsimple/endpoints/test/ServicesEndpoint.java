package com.dnsimple.endpoints.test;

import com.dnsimple.Services;

import com.dnsimple.response.ListServicesResponse;
import com.dnsimple.response.GetServiceResponse;
import com.dnsimple.response.AppliedServicesResponse;
import com.dnsimple.response.ApplyServiceResponse;
import com.dnsimple.response.UnapplyServiceResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;
import java.util.List;

public class ServicesEndpoint implements Services {

  public ListServicesResponse listServices() throws DnsimpleException, IOException {
    return new ListServicesResponse();
  }

  public ListServicesResponse listServices(Map<String, Object> options) throws DnsimpleException, IOException {
    return new ListServicesResponse();
  }

  public GetServiceResponse getService(String serviceId) throws DnsimpleException, IOException {
    return new GetServiceResponse();
  }

  public AppliedServicesResponse appliedServices(String accountId, String domainId) throws DnsimpleException, IOException {
    return new AppliedServicesResponse();
  }

  public AppliedServicesResponse appliedServices(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException {
    return new AppliedServicesResponse();
  }

  public ApplyServiceResponse applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException {
    return new ApplyServiceResponse();
  }

  public UnapplyServiceResponse unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException {
    return new UnapplyServiceResponse();
  }

}


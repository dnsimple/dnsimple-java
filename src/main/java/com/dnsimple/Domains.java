package com.dnsimple;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.GetDomainResponse;
import com.dnsimple.response.CreateDomainResponse;
import com.dnsimple.response.DeleteDomainResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

public class Domains {
  private Client client;

  public Domains(Client client) {
    this.client = client;
  }

  public ListDomainsResponse listDomains(String accountId) throws DnsimpleException, IOException {
    return listDomains(accountId, null);
  }
  public ListDomainsResponse listDomains(String accountId, HashMap<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains", options);
    return (ListDomainsResponse)client.parseResponse(response, ListDomainsResponse.class);
  }

  public GetDomainResponse getDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId);
    return (GetDomainResponse)client.parseResponse(response, GetDomainResponse.class);
  }

  public CreateDomainResponse createDomain(String accountId, HashMap<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains", attributes);
    return (CreateDomainResponse)client.parseResponse(response, CreateDomainResponse.class);
  }

  public DeleteDomainResponse deleteDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId);
    return (DeleteDomainResponse)client.parseResponse(response, DeleteDomainResponse.class);
  }
}

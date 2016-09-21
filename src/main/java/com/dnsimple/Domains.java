package com.dnsimple;

import java.io.IOException;
import java.util.List;

import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.GetDomainResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

public class Domains {
  private Client client;

  public Domains(Client client) {
    this.client = client;
  }

  public ListDomainsResponse listDomains(String accountId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains");
    return (ListDomainsResponse)client.parseResponse(response, ListDomainsResponse.class);
  }

  public GetDomainResponse getDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId);
    return (GetDomainResponse)client.parseResponse(response, GetDomainResponse.class);
  }
}

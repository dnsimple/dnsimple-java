package com.dnsimple.endpoints;

import com.dnsimple.Tlds;
import com.dnsimple.response.ListTldsResponse;
import com.dnsimple.response.GetTldResponse;
import com.dnsimple.response.GetTldExtendedAttributesResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.Map;

public class TldsEndpoint implements Tlds {

  private HttpEndpointClient client;

  public TldsEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListTldsResponse listTlds() throws DnsimpleException, IOException {
    return listTlds(null);
  }

  public ListTldsResponse listTlds(Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get("tlds", options);
    return (ListTldsResponse)client.parseResponse(response, ListTldsResponse.class);
  }

  public GetTldResponse getTld(String tld) throws DnsimpleException, IOException {
    HttpResponse response = client.get("tlds/" + tld);
    return (GetTldResponse)client.parseResponse(response, GetTldResponse.class);
  }

  public GetTldExtendedAttributesResponse getTldExtendedAttributes(String tld) throws DnsimpleException, IOException {
    HttpResponse response = client.get("tlds/" + tld + "/extended_attributes");
    return (GetTldExtendedAttributesResponse)client.parseResponse(response, GetTldExtendedAttributesResponse.class);
  }

}

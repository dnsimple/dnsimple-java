package com.dnsimple;

import java.io.IOException;
import java.util.Map;

import com.dnsimple.response.ListTldsResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple TLDs API.
 *
 * @see https://developer.dnsimple.com/v2/tlds
 */
public class Tlds {

  private Client client;

  protected Tlds(Client client) {
    this.client = client;
  }

  /**
   * Lists supported TLDs for registration
   *
   * @see https://developer.dnsimple.com/v2/tlds/#list
   *
   * @return The list tlds response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListTldsResponse listTlds() throws DnsimpleException, IOException {
    return listTlds(null);
  }

  /**
   * Lists supported TLDs for registration
   *
   * @see https://developer.dnsimple.com/v2/tlds/#list
   *
   * @param options A Map of options to pass to the TLDs API
   * @return The list tlds response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListTldsResponse listTlds(Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get("tlds", options);
    return (ListTldsResponse)client.parseResponse(response, ListTldsResponse.class);
  }


}


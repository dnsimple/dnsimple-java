package com.dnsimple;

import java.io.IOException;
import java.util.Map;

import com.dnsimple.response.ListTldsResponse;
import com.dnsimple.response.GetTldResponse;
import com.dnsimple.response.GetTldExtendedAttributesResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple TLDs API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/tlds">https://developer.dnsimple.com/v2/tlds</a>
 */
public class Tlds {

  private Client client;

  protected Tlds(Client client) {
    this.client = client;
  }

  /**
   * Lists supported TLDs for registration
   *
   * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
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
   * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
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

  /**
   * Get details for a specific tld.
   *
   * @see <a href="https://developer.dnsimple.com/v2/tlds/#get">https://developer.dnsimple.com/v2/tlds/#get</a>
   *
   * @param tld The TLD string (i.e. "com")
   * @return The get tld response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetTldResponse getTld(String tld) throws DnsimpleException, IOException {
    try {
      HttpResponse response = client.get("tlds/" + tld);
      return (GetTldResponse)client.parseResponse(response, GetTldResponse.class);
    } catch(HttpResponseException e) {
      throw DnsimpleException.transformException(e);
    }
  }

  /**
   * Get extended attributes for a TLD
   *
   * @see <a href="https://developer.dnsimple.com/v2/tlds/#extended-attributes">https://developer.dnsimple.com/v2/tlds/#extended-attributes</a>
   *
   * @param tld The TLD to retrieve extended attributes for
   * @return The list tlds response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetTldExtendedAttributesResponse getTldExtendedAttributes(String tld) throws DnsimpleException, IOException {
    HttpResponse response = client.get("tlds/" + tld + "/extended_attributes");
    return (GetTldExtendedAttributesResponse)client.parseResponse(response, GetTldExtendedAttributesResponse.class);
  }

}


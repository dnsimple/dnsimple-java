package com.dnsimple.endpoints.http;

import com.dnsimple.Tlds;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.GetTldExtendedAttributesResponse;
import com.dnsimple.response.GetTldResponse;
import com.dnsimple.response.ListTldsResponse;
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

  public ListTldsResponse listTlds(Map<String, Object> options) throws DnsimpleException, IOException {
    return (ListTldsResponse) client.get("tlds", options, ListTldsResponse.class);
  }

  public GetTldResponse getTld(String tld) throws DnsimpleException, IOException {
    return (GetTldResponse) client.get("tlds/" + tld, null, GetTldResponse.class);
  }

  public GetTldExtendedAttributesResponse getTldExtendedAttributes(String tld) throws DnsimpleException, IOException {
    return (GetTldExtendedAttributesResponse) client.get("tlds/" + tld + "/extended_attributes", null, GetTldExtendedAttributesResponse.class);
  }

}

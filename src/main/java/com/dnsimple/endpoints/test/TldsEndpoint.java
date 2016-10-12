package com.dnsimple.endpoints.test;

import com.dnsimple.Tlds;
import com.dnsimple.response.ListTldsResponse;
import com.dnsimple.response.GetTldResponse;
import com.dnsimple.response.GetTldExtendedAttributesResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

public class TldsEndpoint implements Tlds {

  public ListTldsResponse listTlds() throws DnsimpleException, IOException {
    return new ListTldsResponse();
  }

  public ListTldsResponse listTlds(Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListTldsResponse();
  }

  public GetTldResponse getTld(String tld) throws DnsimpleException, IOException {
    return new GetTldResponse();
  }

  public GetTldExtendedAttributesResponse getTldExtendedAttributes(String tld) throws DnsimpleException, IOException {
    return new GetTldExtendedAttributesResponse();
  }

}

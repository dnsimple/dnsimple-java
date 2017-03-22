package com.dnsimple.endpoints.http;

import com.dnsimple.Identity;
import com.dnsimple.response.WhoamiResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;

public class IdentityEndpoint implements Identity {
  private HttpEndpointClient client;

  public IdentityEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public WhoamiResponse whoami() throws DnsimpleException, IOException {
    HttpResponse response = client.get("whoami");
    return (WhoamiResponse)client.parseResponse(response, WhoamiResponse.class);
  }
}

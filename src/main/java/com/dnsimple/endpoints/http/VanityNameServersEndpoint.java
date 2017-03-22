package com.dnsimple.endpoints.http;

import com.dnsimple.VanityNameServers;
import com.dnsimple.response.EnableVanityNameServersResponse;
import com.dnsimple.response.DisableVanityNameServersResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class VanityNameServersEndpoint implements VanityNameServers {
  private HttpEndpointClient client;

  public VanityNameServersEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public EnableVanityNameServersResponse enableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.put(accountId + "/vanity/" + domainId);
    return (EnableVanityNameServersResponse)client.parseResponse(response, EnableVanityNameServersResponse.class);
  }

  public DisableVanityNameServersResponse disableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/vanity/" + domainId);
    return (DisableVanityNameServersResponse)client.parseResponse(response, DisableVanityNameServersResponse.class);
  }
}


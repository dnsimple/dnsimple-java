package com.dnsimple;

import java.io.IOException;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

public class Identity {
  private Client client;

  public Identity(Client client) {
    this.client = client;
  }

  public WhoamiResponse whoami() throws DnsimpleException, IOException {
    HttpResponse response = client.get("whoami");
    return (WhoamiResponse)client.parseResponse(response, WhoamiResponse.class);
  }
}

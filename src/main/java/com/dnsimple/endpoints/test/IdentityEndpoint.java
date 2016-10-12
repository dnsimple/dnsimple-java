package com.dnsimple.endpoints.test;

import com.dnsimple.Identity;
import com.dnsimple.response.WhoamiResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;

public class IdentityEndpoint implements Identity {

  public WhoamiResponse whoami() throws DnsimpleException, IOException {
    return new WhoamiResponse();
  }

}


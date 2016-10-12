package com.dnsimple.endpoints.test;

import com.dnsimple.VanityNameServers;
import com.dnsimple.response.EnableVanityNameServersResponse;
import com.dnsimple.response.DisableVanityNameServersResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

public class VanityNameServersEndpoint implements VanityNameServers {

  public EnableVanityNameServersResponse enableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException {
    return new EnableVanityNameServersResponse();
  }

  public DisableVanityNameServersResponse disableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException {
    return new DisableVanityNameServersResponse();
  }

}


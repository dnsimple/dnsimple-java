package com.dnsimple.endpoints.http;

import com.dnsimple.VanityNameServers;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.DisableVanityNameServersResponse;
import com.dnsimple.response.EnableVanityNameServersResponse;

import java.io.IOException;

public class VanityNameServersEndpoint implements VanityNameServers {
    private HttpEndpointClient client;

    public VanityNameServersEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public EnableVanityNameServersResponse enableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException {
        return (EnableVanityNameServersResponse) client.put(accountId + "/vanity/" + domainId, null, null, EnableVanityNameServersResponse.class);
    }

    public DisableVanityNameServersResponse disableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException {
        return (DisableVanityNameServersResponse) client.delete(accountId + "/vanity/" + domainId, null, DisableVanityNameServersResponse.class);
    }
}


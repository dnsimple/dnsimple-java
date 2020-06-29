package com.dnsimple.endpoints.http;

import com.dnsimple.Identity;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.WhoamiResponse;

import java.io.IOException;

public class IdentityEndpoint implements Identity {
    private HttpEndpointClient client;

    public IdentityEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public WhoamiResponse whoami() throws DnsimpleException, IOException {
        return (WhoamiResponse) client.get("whoami", null, WhoamiResponse.class);
    }
}

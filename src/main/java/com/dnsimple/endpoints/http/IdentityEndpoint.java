package com.dnsimple.endpoints.http;

import com.dnsimple.Identity;
import com.dnsimple.data.Whoami;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.util.Collections.emptyMap;

public class IdentityEndpoint implements Identity {
    private final HttpEndpointClient client;

    public IdentityEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public SimpleResponse<Whoami> whoami() throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, "whoami", emptyMap(), null, Whoami.class);
    }
}

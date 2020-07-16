package com.dnsimple.endpoints.http;

import com.dnsimple.Identity;
import com.dnsimple.data.WhoamiData;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.util.Collections.emptyMap;

public class IdentityEndpoint implements Identity {
    private final HttpEndpointClient client;

    public IdentityEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public SimpleResponse<WhoamiData> whoami() {
        return client.simple(GET, "whoami", emptyMap(), null, WhoamiData.class);
    }
}

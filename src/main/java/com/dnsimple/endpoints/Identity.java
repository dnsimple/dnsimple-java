package com.dnsimple.endpoints;

import com.dnsimple.data.WhoamiData;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.http.HttpMethod.GET;

/**
 * Provides access to the DNSimple Identity API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/identity">https://developer.dnsimple.com/v2/identity</a>
 */
public class Identity {
    private final HttpEndpointClient client;

    public Identity(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Gets the information about the current authenticated context.
     *
     * @return The whoami response
     * @see <a href="https://developer.dnsimple.com/v2/identity/#whoami">https://developer.dnsimple.com/v2/identity/#whoami</a>
     */
    public SimpleResponse<WhoamiData> whoami() {
        return client.simple(GET, "whoami", ListOptions.empty(), null, WhoamiData.class);
    }
}

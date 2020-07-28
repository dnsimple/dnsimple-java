package com.dnsimple.endpoints;

import com.dnsimple.data.VanityNameServer;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;

import static com.dnsimple.http.HttpMethod.DELETE;
import static com.dnsimple.http.HttpMethod.PUT;

/**
 * Provides access to the  DNSimple Vanity Name Server API
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains/vanity">https://developer.dnsimple.com/v2/domains/vanity</a>
 */
public class VanityNameServers {
    private final HttpEndpointClient client;

    public VanityNameServers(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Enable vanity name servers for the domain
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The enable vanity name server response
     * @see <a href="https://developer.dnsimple.com/v2/domains/vanity/#enableVanityNameServers">https://developer.dnsimple.com/v2/domains/vanity/#enableVanityNameServers</a>
     */
    public ListResponse<VanityNameServer> enableVanityNameServers(Number account, String domain) {
        return client.list(PUT, account + "/vanity/" + domain, ListOptions.empty(), null, VanityNameServer.class);
    }

    /**
     * Disable vanity name servers for the domain
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The disable vanity name server response
     * @see <a href="https://developer.dnsimple.com/v2/domains/vanity/#disableVanityNameServers">https://developer.dnsimple.com/v2/domains/vanity/#disableVanityNameServers</a>
     */
    public EmptyResponse disableVanityNameServers(Number account, String domain) {
        return client.empty(DELETE, account + "/vanity/" + domain, ListOptions.empty(), null);
    }
}


package com.dnsimple;

import com.dnsimple.data.VanityNameServer;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;

/**
 * Provides access to the  DNSimple Vanity Name Server API
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains/vanity">https://developer.dnsimple.com/v2/domains/vanity</a>
 */
public interface VanityNameServers {
    /**
     * Enable vanity name servers for the domain
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The enable vanity name server response
     * @see <a href="https://developer.dnsimple.com/v2/domains/vanity/#enable">https://developer.dnsimple.com/v2/domains/vanity/#enable</a>
     */
    ListResponse<VanityNameServer> enableVanityNameServers(String accountId, String domainId);

    /**
     * Disable vanity name servers for the domain
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The disable vanity name server response
     * @see <a href="https://developer.dnsimple.com/v2/domains/vanity/#disable">https://developer.dnsimple.com/v2/domains/vanity/#disable</a>
     */
    EmptyResponse disableVanityNameServers(String accountId, String domainId);
}

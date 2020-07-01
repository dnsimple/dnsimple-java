package com.dnsimple;

import com.dnsimple.data.NameServer;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

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
     * @throws DnsimpleException Any API error
     * @throws IOException       Any IO error
     * @see <a href="https://developer.dnsimple.com/v2/domains/vanity/#enable">https://developer.dnsimple.com/v2/domains/vanity/#enable</a>
     */
    public ListResponse<NameServer> enableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Disable vanity name servers for the domain
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The disable vanity name server response
     * @throws DnsimpleException Any API error
     * @throws IOException       Any IO error
     * @see <a href="https://developer.dnsimple.com/v2/domains/vanity/#disable">https://developer.dnsimple.com/v2/domains/vanity/#disable</a>
     */
    public EmptyResponse disableVanityNameServers(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;
}

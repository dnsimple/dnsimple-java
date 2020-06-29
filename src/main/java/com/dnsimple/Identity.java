package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.WhoamiResponse;

import java.io.IOException;

/**
 * Provides access to the DNSimple Identity API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/identity">https://developer.dnsimple.com/v2/identity</a>
 */
public interface Identity {
    /**
     * Gets the information about the current authenticated context.
     *
     * @return The whoami response
     * @throws DnsimpleException Any API error
     * @throws IOException       Any IO error
     * @see <a href="https://developer.dnsimple.com/v2/identity/#whoami">https://developer.dnsimple.com/v2/identity/#whoami</a>
     */
    public WhoamiResponse whoami() throws DnsimpleException, IOException;
}

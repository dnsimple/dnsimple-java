package com.dnsimple;

import com.dnsimple.data.Whoami;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;

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
    SimpleResponse<Whoami> whoami() throws DnsimpleException, IOException, InterruptedException;
}

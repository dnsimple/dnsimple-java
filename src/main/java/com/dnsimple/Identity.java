package com.dnsimple;

import com.dnsimple.data.WhoamiData;
import com.dnsimple.response.SimpleResponse;

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
     * @see <a href="https://developer.dnsimple.com/v2/identity/#whoami">https://developer.dnsimple.com/v2/identity/#whoami</a>
     */
    SimpleResponse<WhoamiData> whoami();
}

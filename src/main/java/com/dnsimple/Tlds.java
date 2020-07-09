package com.dnsimple;

import com.dnsimple.data.Tld;
import com.dnsimple.data.TldExtendedAttribute;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

/**
 * Provides access to the DNSimple TLDs API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/tlds">https://developer.dnsimple.com/v2/tlds</a>
 */
public interface Tlds {
    /**
     * Lists supported TLDs for registration
     *
     * @return The list tlds response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
     */
    PaginatedResponse<Tld> listTlds();

    /**
     * Lists supported TLDs for registration
     *
     * @param options A Map of options to pass to the TLDs API
     * @return The list tlds response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
     */
    PaginatedResponse<Tld> listTlds(Map<String, Object> options);

    /**
     * Get details for a specific tld.
     *
     * @param tld The TLD string (i.e. "com")
     * @return The get tld response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#get">https://developer.dnsimple.com/v2/tlds/#get</a>
     */
    SimpleResponse<Tld> getTld(String tld);

    /**
     * Get extended attributes for a TLD
     *
     * @param tld The TLD to retrieve extended attributes for
     * @return The list tlds response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#extended-attributes">https://developer.dnsimple.com/v2/tlds/#extended-attributes</a>
     */
    ListResponse<TldExtendedAttribute> getTldExtendedAttributes(String tld);
}


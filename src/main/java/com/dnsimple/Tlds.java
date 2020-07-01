package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.GetTldExtendedAttributesResponse;
import com.dnsimple.response.GetTldResponse;
import com.dnsimple.response.ListTldsResponse;

import java.io.IOException;
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
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
     */
    public ListTldsResponse listTlds() throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists supported TLDs for registration
     *
     * @param options A Map of options to pass to the TLDs API
     * @return The list tlds response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
     */
    public ListTldsResponse listTlds(Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get details for a specific tld.
     *
     * @param tld The TLD string (i.e. "com")
     * @return The get tld response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#get">https://developer.dnsimple.com/v2/tlds/#get</a>
     */
    public GetTldResponse getTld(String tld) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get extended attributes for a TLD
     *
     * @param tld The TLD to retrieve extended attributes for
     * @return The list tlds response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#extended-attributes">https://developer.dnsimple.com/v2/tlds/#extended-attributes</a>
     */
    public GetTldExtendedAttributesResponse getTldExtendedAttributes(String tld) throws DnsimpleException, IOException, InterruptedException;
}


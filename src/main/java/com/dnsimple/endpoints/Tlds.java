package com.dnsimple.endpoints;

import com.dnsimple.data.Tld;
import com.dnsimple.data.TldExtendedAttribute;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.http.HttpMethod.GET;

/**
 * Provides access to the DNSimple TLDs API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/tlds">https://developer.dnsimple.com/v2/tlds</a>
 */
public class Tlds {
    private final HttpEndpointClient client;

    public Tlds(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists supported TLDs for registration
     *
     * @return The list tlds response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
     */
    public PaginatedResponse<Tld> listTlds() {
        return client.page(GET, "tlds", ListOptions.empty(), null, Tld.class);
    }

    /**
     * Lists supported TLDs for registration
     *
     * @param options The options for the list request
     * @return The list tlds response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#list">https://developer.dnsimple.com/v2/tlds/#list</a>
     */
    public PaginatedResponse<Tld> listTlds(ListOptions options) {
        return client.page(GET, "tlds", options, null, Tld.class);
    }

    /**
     * Get details for a specific tld.
     *
     * @param tld The TLD string (i.e. "com")
     * @return The get tld response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#get">https://developer.dnsimple.com/v2/tlds/#get</a>
     */
    public SimpleResponse<Tld> getTld(String tld) {
        return client.simple(GET, "tlds/" + tld, ListOptions.empty(), null, Tld.class);
    }

    /**
     * Get extended attributes for a TLD
     *
     * @param tld The TLD to retrieve extended attributes for
     * @return The list tlds response
     * @see <a href="https://developer.dnsimple.com/v2/tlds/#extended-attributes">https://developer.dnsimple.com/v2/tlds/#extended-attributes</a>
     */
    public ListResponse<TldExtendedAttribute> getTldExtendedAttributes(String tld) {
        return client.list(GET, "tlds/" + tld + "/extended_attributes", ListOptions.empty(), null, TldExtendedAttribute.class);
    }
}

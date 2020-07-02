package com.dnsimple.endpoints.http;

import com.dnsimple.Tlds;
import com.dnsimple.data.Tld;
import com.dnsimple.data.TldExtendedAttribute;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.util.Collections.emptyMap;

public class TldsEndpoint implements Tlds {
    private final HttpEndpointClient client;

    public TldsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Tld> listTlds() throws DnsimpleException, IOException, InterruptedException {
        return listTlds(null);
    }

    public PaginatedResponse<Tld> listTlds(Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, "tlds", options, null, Tld.class);
    }

    public SimpleResponse<Tld> getTld(String tld) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, "tlds/" + tld, emptyMap(), null, Tld.class);
    }

    public ListResponse<TldExtendedAttribute> getTldExtendedAttributes(String tld) throws DnsimpleException, IOException, InterruptedException {
        return client.list(GET, "tlds/" + tld + "/extended_attributes", emptyMap(), null, TldExtendedAttribute.class);
    }
}

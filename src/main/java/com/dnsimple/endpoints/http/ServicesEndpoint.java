package com.dnsimple.endpoints.http;

import com.dnsimple.Services;
import com.dnsimple.data.Service;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.dnsimple.endpoints.http.java11.HttpMethod.GET;
import static java.util.Collections.emptyMap;

public class ServicesEndpoint implements Services {
    private final HttpEndpointClient client;

    public ServicesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Service> listServices() throws DnsimpleException, IOException, InterruptedException {
        return listServices(emptyMap());
    }

    public ListResponse<Service> listServices(Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.list(GET, "services", options, emptyMap(), Service.class);
    }

    public SimpleResponse<Service> getService(String serviceId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, "services/" + serviceId, null, Collections.emptyMap(), Service.class);
    }

    public PaginatedResponse<Service> appliedServices(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return appliedServices(accountId, domainId, emptyMap());
    }

    public PaginatedResponse<Service> appliedServices(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, accountId + "/domains/" + domainId + "/services", options, Service.class);
    }

    public SimpleResponse<Service> applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/domains/" + domainId + "/services/" + serviceId, settings, null, Service.class);
    }

    public SimpleResponse<Service> unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteSimple(accountId + "/domains/" + domainId + "/services/" + serviceId, null, Service.class);
    }
}

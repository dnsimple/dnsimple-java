package com.dnsimple.endpoints.http;

import com.dnsimple.Services;
import com.dnsimple.data.Service;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

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
        return client.getList("services", options, Service.class);
    }

    public SimpleResponse<Service> getService(String serviceId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple("services/" + serviceId, null, Service.class);
    }

    public PaginatedResponse<Service> appliedServices(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return appliedServices(accountId, domainId, emptyMap());
    }

    public PaginatedResponse<Service> appliedServices(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.getPage(accountId + "/domains/" + domainId + "/services", options, Service.class);
    }

    public SimpleResponse<Service> applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/domains/" + domainId + "/services/" + serviceId, settings, null, Service.class);
    }

    public SimpleResponse<Service> unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteSimple(accountId + "/domains/" + domainId + "/services/" + serviceId, null, Service.class);
    }
}

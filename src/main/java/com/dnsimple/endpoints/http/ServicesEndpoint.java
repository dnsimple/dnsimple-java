package com.dnsimple.endpoints.http;

import com.dnsimple.Services;
import com.dnsimple.data.Service;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.emptyMap;

public class ServicesEndpoint implements Services {
    private final HttpEndpointClient client;

    public ServicesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Service> listServices() {
        return client.list(GET, "services", emptyMap(), null, Service.class);
    }

    public ListResponse<Service> listServices(Map<String, Object> options) {
        return client.list(GET, "services", options, null, Service.class);
    }

    public SimpleResponse<Service> getService(String serviceId) {
        return client.simple(GET, "services/" + serviceId, emptyMap(), null, Service.class);
    }

    public PaginatedResponse<Service> appliedServices(String accountId, String domainId) {
        return client.page(GET, accountId + "/domains/" + domainId + "/services", emptyMap(), null, Service.class);
    }

    public PaginatedResponse<Service> appliedServices(String accountId, String domainId, Map<String, Object> options) {
        return client.page(GET, accountId + "/domains/" + domainId + "/services", options, null, Service.class);
    }

    public SimpleResponse<Service> applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) {
        return client.simple(POST, accountId + "/domains/" + domainId + "/services/" + serviceId, emptyMap(), settings, Service.class);
    }

    public SimpleResponse<Service> unapplyService(String accountId, String domainId, String serviceId) {
        return client.simple(DELETE, accountId + "/domains/" + domainId + "/services/" + serviceId, emptyMap(), null, Service.class);
    }
}

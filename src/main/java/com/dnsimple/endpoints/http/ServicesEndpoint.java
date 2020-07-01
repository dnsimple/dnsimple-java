package com.dnsimple.endpoints.http;

import com.dnsimple.Services;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServicesEndpoint implements Services {
    private HttpEndpointClient client;

    public ServicesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListServicesResponse listServices() throws DnsimpleException, IOException, InterruptedException {
        return listServices(new HashMap<String, Object>());
    }

    public ListServicesResponse listServices(Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListServicesResponse) client.get("services", options, ListServicesResponse.class);
    }

    public GetServiceResponse getService(String serviceId) throws DnsimpleException, IOException, InterruptedException {
        return (GetServiceResponse) client.get("services/" + serviceId, null, GetServiceResponse.class);
    }

    public AppliedServicesResponse appliedServices(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return appliedServices(accountId, domainId, new HashMap<String, Object>());
    }

    public AppliedServicesResponse appliedServices(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (AppliedServicesResponse) client.get(accountId + "/domains/" + domainId + "/services", options, AppliedServicesResponse.class);
    }

    public ApplyServiceResponse applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException, InterruptedException {
        return (ApplyServiceResponse) client.post(accountId + "/domains/" + domainId + "/services/" + serviceId, settings, null, ApplyServiceResponse.class);
    }

    public UnapplyServiceResponse unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException, InterruptedException {
        return (UnapplyServiceResponse) client.delete(accountId + "/domains/" + domainId + "/services/" + serviceId, null, UnapplyServiceResponse.class);
    }
}

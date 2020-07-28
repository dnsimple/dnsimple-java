package com.dnsimple.endpoints;

import com.dnsimple.data.Service;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static java.util.Collections.singletonMap;

/**
 * Provides access to the DNSimple one-click Services API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/services">https://developer.dnsimple.com/v2/services</a>
 */
public class Services {
    private final HttpEndpointClient client;

    public Services(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the available one-click services.
     *
     * @return The list services response
     * @see <a href="https://developer.dnsimple.com/v2/services/#list">https://developer.dnsimple.com/v2/services/#list</a>
     */
    public ListResponse<Service> listServices() {
        return client.list(GET, "services", ListOptions.empty(), null, Service.class);
    }

    /**
     * Lists the available one-click services.
     *
     * @param options The options for the list request
     * @return The list services response
     * @see <a href="https://developer.dnsimple.com/v2/services/#listServices">https://developer.dnsimple.com/v2/services/#listServices</a>
     */
    public ListResponse<Service> listServices(ListOptions options) {
        return client.list(GET, "services", options, null, Service.class);
    }

    /**
     * Get a specific service by ID.
     *
     * @param service The service name or ID
     * @return The get service response
     * @see <a href="https://developer.dnsimple.com/v2/services/#getService">https://developer.dnsimple.com/v2/services/#getService</a>
     */
    public SimpleResponse<Service> getService(String service) {
        return client.simple(GET, "services/" + service, ListOptions.empty(), null, Service.class);
    }

    /**
     * Lists the one-click services applied to the domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The applied services response
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#listDomainAppliedServices">https://developer.dnsimple.com/v2/services/domains/#listDomainAppliedServices</a>
     */
    public PaginatedResponse<Service> appliedServices(Number account, String domain) {
        return client.page(GET, account + "/domains/" + domain + "/services", ListOptions.empty(), null, Service.class);
    }

    /**
     * Lists the one-click services applied to the domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the list request
     * @return The applied services response
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#listDomainAppliedServices">https://developer.dnsimple.com/v2/services/domains/#listDomainAppliedServices</a>
     */
    public PaginatedResponse<Service> appliedServices(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/services", options, null, Service.class);
    }

    /**
     * Apply the given one-click service to the given domain.
     *
     * @param account  The account ID
     * @param domain   The domain name or ID
     * @param service  The service name or ID to apply
     * @param settings A Map of settings for the service
     * @return The apply service response
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#applyServiceToDomain">https://developer.dnsimple.com/v2/services/domains/#applyServiceToDomain</a>
     */
    public SimpleResponse<Service> applyService(Number account, String domain, String service, Map<String, Object> settings) {
        return client.simple(POST, account + "/domains/" + domain + "/services/" + service, ListOptions.empty(), singletonMap("settings", settings), Service.class);
    }

    /**
     * Unapply the given one-click service  the given domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param service The service name or ID to unapply
     * @return The unapply service response
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#unapplyServiceFromDomain">https://developer.dnsimple.com/v2/services/domains/#unapplyServiceFromDomain</a>
     */
    public SimpleResponse<Service> unapplyService(Number account, String domain, String service) {
        return client.simple(DELETE, account + "/domains/" + domain + "/services/" + service, ListOptions.empty(), null, Service.class);
    }
}

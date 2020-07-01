package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.Map;

/**
 * Provides access to the DNSimple one-click Services API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/services">https://developer.dnsimple.com/v2/services</a>
 */
public interface Services {
    /**
     * Lists the available one-click services.
     *
     * @return The list services response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/#list">https://developer.dnsimple.com/v2/services/#list</a>
     */
    public ListServicesResponse listServices() throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the available one-click services.
     *
     * @param options Options to pass to the DNSimple API
     * @return The list services response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/#list">https://developer.dnsimple.com/v2/services/#list</a>
     */
    public ListServicesResponse listServices(Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get a specific service by ID.
     *
     * @param serviceId The service ID
     * @return The get service response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/#get">https://developer.dnsimple.com/v2/services/#get</a>
     */
    public GetServiceResponse getService(String serviceId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the one-click services applied to the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The applied services response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#applied">https://developer.dnsimple.com/v2/services/domains/#applied</a>
     */
    public AppliedServicesResponse appliedServices(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the one-click services applied to the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @param options   Options passed to the DNSimple API
     * @return The applied services response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#applied">https://developer.dnsimple.com/v2/services/domains/#applied</a>
     */
    public AppliedServicesResponse appliedServices(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Apply the given one-click service to the given domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @param serviceId The service ID to apply
     * @param settings  A Map of settings for the service
     * @return The apply service response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#apply">https://developer.dnsimple.com/v2/services/domains/#apply</a>
     */
    public ApplyServiceResponse applyService(String accountId, String domainId, String serviceId, Map<String, Object> settings) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Unapply the given one-click service  the given domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @param serviceId The service ID to unapply
     * @return The unapply service response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/services/domains/#apply">https://developer.dnsimple.com/v2/services/domains/#apply</a>
     */
    public UnapplyServiceResponse unapplyService(String accountId, String domainId, String serviceId) throws DnsimpleException, IOException, InterruptedException;
}

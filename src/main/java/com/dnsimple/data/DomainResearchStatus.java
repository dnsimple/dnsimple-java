package com.dnsimple.data;

import java.util.List;

/**
 * Domain research status result.
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains/research/#getDomainsResearchStatus">Get Domains Research Status</a>
 */
public class DomainResearchStatus {
    private final String requestId;
    private final String domain;
    private final String availability;
    private final List<String> errors;

    public DomainResearchStatus(String requestId, String domain, String availability, List<String> errors) {
        this.requestId = requestId;
        this.domain = domain;
        this.availability = availability;
        this.errors = errors;
    }

    /**
     * UUID identifier for this research request.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * The domain name that was researched.
     */
    public String getDomain() {
        return domain;
    }

    /**
     * The availability status (e.g. "available", "unavailable").
     *
     * @see <a href="https://developer.dnsimple.com/v2/domains/research/#getDomainsResearchStatus">API documentation</a>
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * Array of error messages if the domain cannot be researched.
     */
    public List<String> getErrors() {
        return errors;
    }
}

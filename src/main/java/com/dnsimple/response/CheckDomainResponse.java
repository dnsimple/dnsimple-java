package com.dnsimple.response;

import com.dnsimple.data.DomainAvailability;

public class CheckDomainResponse extends ApiResponse {
    private DomainAvailability data;

    public CheckDomainResponse() {
        this(new DomainAvailability());
    }

    public CheckDomainResponse(DomainAvailability data) {
        this.data = data;
    }

    public DomainAvailability getData() {
        return data;
    }
}

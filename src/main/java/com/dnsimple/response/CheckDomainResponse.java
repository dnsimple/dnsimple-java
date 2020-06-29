package com.dnsimple.response;

import com.dnsimple.data.DomainAvailability;

public class CheckDomainResponse extends ApiResponse {
    private final DomainAvailability data;

    public CheckDomainResponse() {
        data = null;
    }

    public CheckDomainResponse(DomainAvailability data) {
        this.data = data;
    }

    public DomainAvailability getData() {
        return data;
    }
}

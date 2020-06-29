package com.dnsimple.response;

import com.dnsimple.data.DomainRenewal;

public class RenewDomainResponse extends ApiResponse {
    private final DomainRenewal data;

    public RenewDomainResponse() {
        data = null;
    }

    public RenewDomainResponse(DomainRenewal data) {
        this.data = data;
    }

    public DomainRenewal getData() {
        return data;
    }
}

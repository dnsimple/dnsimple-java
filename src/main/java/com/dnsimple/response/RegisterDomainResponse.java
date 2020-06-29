package com.dnsimple.response;

import com.dnsimple.data.DomainRegistration;

public class RegisterDomainResponse extends ApiResponse {
    private final DomainRegistration data;

    public RegisterDomainResponse() {
        data = null;
    }

    public RegisterDomainResponse(DomainRegistration data) {
        this.data = data;
    }

    public DomainRegistration getData() {
        return data;
    }
}


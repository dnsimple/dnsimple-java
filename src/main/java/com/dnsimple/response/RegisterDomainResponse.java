package com.dnsimple.response;

import com.dnsimple.data.DomainRegistration;

public class RegisterDomainResponse extends ApiResponse {
    private DomainRegistration data;

    public DomainRegistration getData() {
        return data;
    }
}


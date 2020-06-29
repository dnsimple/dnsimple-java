package com.dnsimple.response;

import com.dnsimple.data.Domain;

public class CreateDomainResponse extends ApiResponse {
    private final Domain data;

    public CreateDomainResponse() {
        data = null;
    }

    public CreateDomainResponse(Domain data) {
        this.data = data;
    }

    public Domain getData() {
        return data;
    }
}

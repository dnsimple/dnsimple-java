package com.dnsimple.response;

import com.dnsimple.data.Domain;

public class GetDomainResponse extends ApiResponse {
    private final Domain data;

    public GetDomainResponse() {
        data = null;
    }

    public GetDomainResponse(Domain data) {
        this.data = data;
    }

    public Domain getData() {
        return data;
    }
}

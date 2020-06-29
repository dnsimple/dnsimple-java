package com.dnsimple.response;

import com.dnsimple.data.Domain;

public class ResetDomainTokenResponse extends ApiResponse {
    private final Domain data;

    public ResetDomainTokenResponse() {
        data = null;
    }

    public ResetDomainTokenResponse(Domain data) {
        this.data = data;
    }

    public Domain getData() {
        return data;
    }
}


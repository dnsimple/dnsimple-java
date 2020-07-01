package com.dnsimple.response;

import com.dnsimple.data.Domain;

public class GetDomainResponse extends ApiResponse {
    private Domain data;

    public Domain getData() {
        return data;
    }
}

package com.dnsimple.response;

import java.util.List;

import static java.util.Collections.emptyList;

public class GetDomainDelegationResponse extends ApiResponse {
    private final List<String> data;

    public GetDomainDelegationResponse() {
        data = emptyList();
    }

    public GetDomainDelegationResponse(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }
}

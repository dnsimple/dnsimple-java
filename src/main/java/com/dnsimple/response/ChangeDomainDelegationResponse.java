package com.dnsimple.response;

import java.util.List;

public class ChangeDomainDelegationResponse extends ApiResponse {
    private final List<String> data;

    public ChangeDomainDelegationResponse() {
        data = null;
    }

    public ChangeDomainDelegationResponse(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }
}

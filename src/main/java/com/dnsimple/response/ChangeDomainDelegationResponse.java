package com.dnsimple.response;

import java.util.ArrayList;
import java.util.List;

public class ChangeDomainDelegationResponse extends ApiResponse {
    private List<String> data;

    public ChangeDomainDelegationResponse() {
        this(new ArrayList<String>());
    }

    public ChangeDomainDelegationResponse(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }
}

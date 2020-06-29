package com.dnsimple.response;

import com.dnsimple.data.NameServer;

import java.util.List;

import static java.util.Collections.emptyList;

public class ChangeDomainDelegationToVanityResponse extends ApiResponse {
    private final List<NameServer> data;

    public ChangeDomainDelegationToVanityResponse() {
        data = emptyList();
    }

    public ChangeDomainDelegationToVanityResponse(List<NameServer> data) {
        this.data = data;
    }

    public List<NameServer> getData() {
        return data;
    }
}

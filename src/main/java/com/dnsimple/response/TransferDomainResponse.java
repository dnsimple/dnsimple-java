package com.dnsimple.response;

import com.dnsimple.data.DomainTransfer;

public class TransferDomainResponse extends ApiResponse {
    private final DomainTransfer data;

    public TransferDomainResponse() {
        data = null;
    }

    public TransferDomainResponse(DomainTransfer data) {
        this.data = data;
    }

    public DomainTransfer getData() {
        return data;
    }
}

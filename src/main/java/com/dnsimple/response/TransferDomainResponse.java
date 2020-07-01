package com.dnsimple.response;

import com.dnsimple.data.DomainTransfer;

public class TransferDomainResponse extends ApiResponse {
    private DomainTransfer data;

    public DomainTransfer getData() {
        return data;
    }
}

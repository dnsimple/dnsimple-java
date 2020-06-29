package com.dnsimple.response;

import com.dnsimple.data.DelegationSignerRecord;

public class CreateDelegationSignerRecordResponse extends ApiResponse {
    private final DelegationSignerRecord data;

    public CreateDelegationSignerRecordResponse() {
        data = null;
    }

    public CreateDelegationSignerRecordResponse(DelegationSignerRecord data) {
        this.data = data;
    }

    public DelegationSignerRecord getData() {
        return data;
    }
}


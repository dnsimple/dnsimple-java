package com.dnsimple.response;

import com.dnsimple.data.DelegationSignerRecord;

public class GetDelegationSignerRecordResponse extends ApiResponse {
    private final DelegationSignerRecord data;

    public GetDelegationSignerRecordResponse() {
        data = null;
    }

    public GetDelegationSignerRecordResponse(DelegationSignerRecord data) {
        this.data = data;
    }

    public DelegationSignerRecord getData() {
        return data;
    }
}


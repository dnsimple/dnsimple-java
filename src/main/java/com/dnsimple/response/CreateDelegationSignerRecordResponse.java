package com.dnsimple.response;

import com.dnsimple.data.DelegationSignerRecord;

public class CreateDelegationSignerRecordResponse extends ApiResponse {
    private DelegationSignerRecord data;

    public DelegationSignerRecord getData() {
        return data;
    }
}


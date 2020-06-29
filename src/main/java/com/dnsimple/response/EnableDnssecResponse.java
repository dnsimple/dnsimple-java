package com.dnsimple.response;

import com.dnsimple.data.Dnssec;

public class EnableDnssecResponse extends ApiResponse {
    private final Dnssec data;

    public EnableDnssecResponse() {
        data = null;
    }

    public EnableDnssecResponse(Dnssec data) {
        this.data = data;
    }

    public Dnssec getData() {
        return data;
    }
}

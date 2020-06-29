package com.dnsimple.response;

import com.dnsimple.data.Dnssec;

public class GetDnssecResponse extends ApiResponse {
    private final Dnssec data;

    public GetDnssecResponse() {
        data = null;
    }

    public GetDnssecResponse(Dnssec data) {
        this.data = data;
    }

    public Dnssec getData() {
        return data;
    }
}

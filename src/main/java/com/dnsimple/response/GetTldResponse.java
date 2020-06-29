package com.dnsimple.response;

import com.dnsimple.data.Tld;

public class GetTldResponse extends ApiResponse {
    private final Tld data;

    public GetTldResponse() {
        data = null;
    }

    public GetTldResponse(Tld data) {
        this.data = data;
    }

    public Tld getData() {
        return data;
    }
}

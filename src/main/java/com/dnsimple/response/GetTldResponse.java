package com.dnsimple.response;

import com.dnsimple.data.Tld;

public class GetTldResponse extends ApiResponse {
    private Tld data;

    public Tld getData() {
        return data;
    }
}

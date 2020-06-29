package com.dnsimple.response;

import com.dnsimple.data.Certificate;

public class IssueLetsencryptRenewalResponse extends ApiResponse {
    private final Certificate data;

    public IssueLetsencryptRenewalResponse() {
        data = null;
    }

    public IssueLetsencryptRenewalResponse(Certificate data) {
        this.data = data;
    }

    public Certificate getData() {
        return data;
    }
}

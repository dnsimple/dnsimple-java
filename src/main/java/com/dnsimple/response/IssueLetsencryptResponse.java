package com.dnsimple.response;

import com.dnsimple.data.Certificate;

public class IssueLetsencryptResponse extends ApiResponse {
    private final Certificate data;

    public IssueLetsencryptResponse() {
        data = null;
    }

    public IssueLetsencryptResponse(Certificate data) {
        this.data = data;
    }

    public Certificate getData() {
        return data;
    }
}

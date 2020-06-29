package com.dnsimple.response;

import com.dnsimple.data.Certificate;

public class GetCertificateResponse extends ApiResponse {
    private final Certificate data;

    public GetCertificateResponse() {
        data = null;
    }

    public GetCertificateResponse(Certificate data) {
        this.data = data;
    }

    public Certificate getData() {
        return data;
    }
}

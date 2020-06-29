package com.dnsimple.response;

import com.dnsimple.data.CertificateBundle;

public class GetCertificatePrivateKeyResponse extends ApiResponse {
    private final CertificateBundle data;

    public GetCertificatePrivateKeyResponse() {
        data = null;
    }

    public GetCertificatePrivateKeyResponse(CertificateBundle data) {
        this.data = data;
    }

    public CertificateBundle getData() {
        return data;
    }
}

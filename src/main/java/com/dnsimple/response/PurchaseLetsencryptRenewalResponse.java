package com.dnsimple.response;

import com.dnsimple.data.CertificateRenewal;

public class PurchaseLetsencryptRenewalResponse extends ApiResponse {
    private final CertificateRenewal data;

    public PurchaseLetsencryptRenewalResponse() {
        data = null;
    }

    public PurchaseLetsencryptRenewalResponse(CertificateRenewal data) {
        this.data = data;
    }

    public CertificateRenewal getData() {
        return data;
    }
}

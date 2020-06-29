package com.dnsimple.response;

import com.dnsimple.data.CertificatePurchase;

public class PurchaseLetsencryptResponse extends ApiResponse {
    private final CertificatePurchase data;

    public PurchaseLetsencryptResponse() {
        data = null;
    }

    public PurchaseLetsencryptResponse(CertificatePurchase data) {
        this.data = data;
    }

    public CertificatePurchase getData() {
        return data;
    }
}

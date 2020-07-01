package com.dnsimple.response;

import com.dnsimple.data.CertificatePurchase;



public class PurchaseLetsencryptResponse extends ApiResponse {

  private CertificatePurchase data;

    public PurchaseLetsencryptResponse() {
    this(new CertificatePurchase());
    }

    public PurchaseLetsencryptResponse(CertificatePurchase data) {
        this.data = data;
    }

    public CertificatePurchase getData() {
        return data;
    }
}

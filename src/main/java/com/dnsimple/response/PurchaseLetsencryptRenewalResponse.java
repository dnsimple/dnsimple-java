package com.dnsimple.response;

import com.dnsimple.data.CertificateRenewal;


public class PurchaseLetsencryptRenewalResponse extends ApiResponse {

  private CertificateRenewal data;

    public PurchaseLetsencryptRenewalResponse() {
    this(new CertificateRenewal());
    }

    public PurchaseLetsencryptRenewalResponse(CertificateRenewal data) {
        this.data = data;
    }

    public CertificateRenewal getData() {
        return data;
    }
}

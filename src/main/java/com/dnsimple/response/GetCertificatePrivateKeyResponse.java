package com.dnsimple.response;

import com.dnsimple.data.CertificateBundle;



public class GetCertificatePrivateKeyResponse extends ApiResponse {

  private CertificateBundle data;

    public GetCertificatePrivateKeyResponse() {
    this(new CertificateBundle());
    }

    public GetCertificatePrivateKeyResponse(CertificateBundle data) {
        this.data = data;
    }

    public CertificateBundle getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.CertificateBundle;



public class DownloadCertificateResponse extends ApiResponse {

  private CertificateBundle data;

    public DownloadCertificateResponse() {
    this(new CertificateBundle());
    }

    public DownloadCertificateResponse(CertificateBundle data) {
        this.data = data;
    }

    public CertificateBundle getData() {
        return data;
    }
}

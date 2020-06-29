package com.dnsimple.response;

import com.dnsimple.data.CertificateBundle;

public class DownloadCertificateResponse extends ApiResponse {
    private final CertificateBundle data;

    public DownloadCertificateResponse() {
        data = null;
    }

    public DownloadCertificateResponse(CertificateBundle data) {
        this.data = data;
    }

    public CertificateBundle getData() {
        return data;
    }
}

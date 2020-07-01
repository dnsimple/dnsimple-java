package com.dnsimple.response;

import com.dnsimple.data.Certificate;



public class GetCertificateResponse extends ApiResponse {

  private Certificate data;

    public GetCertificateResponse() {
    this(new Certificate());
    }

    public GetCertificateResponse(Certificate data) {
        this.data = data;
    }

    public Certificate getData() {
        return data;
    }
}

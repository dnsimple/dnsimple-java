package com.dnsimple.response;

import com.dnsimple.data.Certificate;


public class IssueLetsencryptRenewalResponse extends ApiResponse {

  private Certificate data;

    public IssueLetsencryptRenewalResponse() {
    this(new Certificate());
    }

    public IssueLetsencryptRenewalResponse(Certificate data) {
        this.data = data;
    }

    public Certificate getData() {
        return data;
    }
}

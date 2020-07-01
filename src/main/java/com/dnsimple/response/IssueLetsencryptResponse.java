package com.dnsimple.response;

import com.dnsimple.data.Certificate;


public class IssueLetsencryptResponse extends ApiResponse {

  private Certificate data;

    public IssueLetsencryptResponse() {
    this(new Certificate());
    }

    public IssueLetsencryptResponse(Certificate data) {
        this.data = data;
    }

    public Certificate getData() {
        return data;
    }
}

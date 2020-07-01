package com.dnsimple.response;

import com.dnsimple.data.Dnssec;



public class EnableDnssecResponse extends ApiResponse {

  private Dnssec data;

    public Dnssec getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.DomainRenewal;



public class RenewDomainResponse extends ApiResponse {

  private DomainRenewal data;

    public DomainRenewal getData() {
        return data;
    }
}

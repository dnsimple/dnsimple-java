package com.dnsimple.response;

import java.util.List;



public class GetDomainDelegationResponse extends ApiResponse {

  private List<String> data;

    public List<String> getData() {
        return data;
    }
}

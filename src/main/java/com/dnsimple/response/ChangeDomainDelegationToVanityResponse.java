package com.dnsimple.response;

import com.dnsimple.data.NameServer;



import java.util.List;
import java.util.ArrayList;

public class ChangeDomainDelegationToVanityResponse extends ApiResponse {

  private List<NameServer> data;

    public ChangeDomainDelegationToVanityResponse() {
    this(new ArrayList<NameServer>());
    }

    public ChangeDomainDelegationToVanityResponse(List<NameServer> data) {
        this.data = data;
    }

    public List<NameServer> getData() {
        return data;
    }
}

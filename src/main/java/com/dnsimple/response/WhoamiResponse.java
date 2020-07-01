package com.dnsimple.response;

import com.dnsimple.data.Whoami;



public class WhoamiResponse extends ApiResponse {

  private Whoami data;

    public WhoamiResponse() {
    this(new Whoami());
    }

    public WhoamiResponse(Whoami data) {
        this.data = data;
    }

    public Whoami getData() {
        return data;
    }
}

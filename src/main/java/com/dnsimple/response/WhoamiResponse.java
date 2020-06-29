package com.dnsimple.response;

import com.dnsimple.data.Whoami;

public class WhoamiResponse extends ApiResponse {
    private final Whoami data;

    public WhoamiResponse() {
        data = null;
    }

    public WhoamiResponse(Whoami data) {
        this.data = data;
    }

    public Whoami getData() {
        return data;
    }
}

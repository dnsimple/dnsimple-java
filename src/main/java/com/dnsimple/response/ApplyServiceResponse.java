package com.dnsimple.response;

import com.dnsimple.data.Service;

public class ApplyServiceResponse extends ApiResponse {
    private final Service data;

    public ApplyServiceResponse() {
        data = null;
    }

    public ApplyServiceResponse(Service data) {
        this.data = data;
    }

    public Service getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.Service;

public class GetServiceResponse extends ApiResponse {
    private final Service data;

    public GetServiceResponse() {
        data = null;
    }

    public GetServiceResponse(Service data) {
        this.data = data;
    }

    public Service getData() {
        return data;
    }
}

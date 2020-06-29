package com.dnsimple.response;

import com.dnsimple.data.Service;

public class UnapplyServiceResponse extends ApiResponse {
    private final Service data;

    public UnapplyServiceResponse() {
        data = null;
    }

    public UnapplyServiceResponse(Service data) {
        this.data = data;
    }

    public Service getData() {
        return data;
    }
}

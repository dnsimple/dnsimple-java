package com.dnsimple.response;

import com.dnsimple.data.Service;

public class GetServiceResponse extends ApiResponse {
    private Service data;

    public Service getData() {
        return data;
    }
}

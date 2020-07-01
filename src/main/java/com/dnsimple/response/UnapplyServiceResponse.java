package com.dnsimple.response;

import com.dnsimple.data.Service;

public class UnapplyServiceResponse extends ApiResponse {
    private Service data;

    public Service getData() {
        return data;
    }
}

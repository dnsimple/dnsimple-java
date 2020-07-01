package com.dnsimple.response;

import com.dnsimple.data.Service;

import java.util.List;

public class ListServicesResponse extends ApiResponse {
    private List<Service> data;

    public List<Service> getData() {
        return data;
    }
}

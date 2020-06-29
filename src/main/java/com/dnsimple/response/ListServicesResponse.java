package com.dnsimple.response;

import com.dnsimple.data.Service;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListServicesResponse extends ApiResponse {
    private final List<Service> data;

    public ListServicesResponse() {
        data = emptyList();
    }

    public ListServicesResponse(List<Service> data) {
        this.data = data;
    }

    public List<Service> getData() {
        return data;
    }
}

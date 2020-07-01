package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Service;

import java.util.ArrayList;
import java.util.List;

public class AppliedServicesResponse extends ApiResponse {
    private List<Service> data;
    private Pagination pagination;

    public AppliedServicesResponse() {
        this(new ArrayList<Service>());
    }

    public AppliedServicesResponse(List<Service> data) {
        this(data, new Pagination());
    }

    public AppliedServicesResponse(List<Service> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Service> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

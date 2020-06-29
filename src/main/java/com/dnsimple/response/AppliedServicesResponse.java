package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Service;

import java.util.List;

import static java.util.Collections.emptyList;

public class AppliedServicesResponse extends ApiResponse {
    private final List<Service> data;
    private final Pagination pagination;

    public AppliedServicesResponse() {
        data = emptyList();
        pagination = Pagination.empty();
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

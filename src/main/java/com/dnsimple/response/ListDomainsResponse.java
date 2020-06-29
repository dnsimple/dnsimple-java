package com.dnsimple.response;

import com.dnsimple.data.Domain;
import com.dnsimple.data.Pagination;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListDomainsResponse extends ApiResponse {
    private final List<Domain> data;
    private final Pagination pagination;

    public ListDomainsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListDomainsResponse(List<Domain> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Domain> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

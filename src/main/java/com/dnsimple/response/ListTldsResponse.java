package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Tld;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListTldsResponse extends ApiResponse {
    private final List<Tld> data;
    private final Pagination pagination;

    public ListTldsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListTldsResponse(List<Tld> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Tld> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

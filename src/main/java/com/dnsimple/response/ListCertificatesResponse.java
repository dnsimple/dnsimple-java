package com.dnsimple.response;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.Pagination;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListCertificatesResponse extends ApiResponse {
    private final List<Certificate> data;
    private final Pagination pagination;

    public ListCertificatesResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListCertificatesResponse(List<Certificate> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Certificate> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

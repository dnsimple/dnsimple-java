package com.dnsimple.response;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.Pagination;

import java.util.ArrayList;
import java.util.List;

public class ListCertificatesResponse extends ApiResponse {
    private List<Certificate> data;
    private Pagination pagination;

    public ListCertificatesResponse() {
        this(new ArrayList<Certificate>());
    }

    public ListCertificatesResponse(List<Certificate> data) {
        this(data, new Pagination());
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

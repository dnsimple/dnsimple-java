package com.dnsimple.response;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.data.Pagination;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class ListDelegationSignerRecordsResponse extends ApiResponse {
    private final List<DelegationSignerRecord> data;
    private final Pagination pagination;

    public ListDelegationSignerRecordsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListDelegationSignerRecordsResponse(List<DelegationSignerRecord> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<DelegationSignerRecord> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}


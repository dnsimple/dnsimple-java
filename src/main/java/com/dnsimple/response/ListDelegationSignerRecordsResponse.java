package com.dnsimple.response;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.data.Pagination;

import java.util.ArrayList;
import java.util.List;

public class ListDelegationSignerRecordsResponse extends ApiResponse {
    private List<DelegationSignerRecord> data;
    private Pagination pagination;

    public ListDelegationSignerRecordsResponse() {
        this(new ArrayList<DelegationSignerRecord>());
    }

    public ListDelegationSignerRecordsResponse(List<DelegationSignerRecord> data) {
        this(data, new Pagination());
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


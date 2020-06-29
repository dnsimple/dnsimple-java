package com.dnsimple.response;

import com.dnsimple.data.EmailForward;
import com.dnsimple.data.Pagination;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListEmailForwardsResponse extends ApiResponse {
    private final List<EmailForward> data;
    private final Pagination pagination;

    public ListEmailForwardsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListEmailForwardsResponse(List<EmailForward> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<EmailForward> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

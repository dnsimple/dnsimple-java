package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Push;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListPushesResponse extends ApiResponse {
    private final List<Push> data;
    private final Pagination pagination;

    public ListPushesResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListPushesResponse(List<Push> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Push> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

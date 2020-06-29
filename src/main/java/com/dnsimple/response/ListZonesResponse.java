package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Zone;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListZonesResponse extends ApiResponse {
    private final List<Zone> data;
    private final Pagination pagination;

    public ListZonesResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListZonesResponse(List<Zone> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Zone> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

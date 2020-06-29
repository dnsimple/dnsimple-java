package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.ZoneRecord;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListZoneRecordsResponse extends ApiResponse {
    private final List<ZoneRecord> data;
    private final Pagination pagination;

    public ListZoneRecordsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListZoneRecordsResponse(List<ZoneRecord> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<ZoneRecord> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

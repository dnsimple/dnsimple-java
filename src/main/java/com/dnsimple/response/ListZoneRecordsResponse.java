package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.ZoneRecord;

import java.util.List;

public class ListZoneRecordsResponse extends ApiResponse {
    private List<ZoneRecord> data;
    private Pagination pagination;

    public List<ZoneRecord> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

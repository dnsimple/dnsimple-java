package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Zone;

import java.util.List;

public class ListZonesResponse extends ApiResponse {
    private List<Zone> data;
    private Pagination pagination;

    public List<Zone> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

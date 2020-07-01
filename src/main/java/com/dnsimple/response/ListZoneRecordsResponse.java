package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;
import com.dnsimple.data.Pagination;

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

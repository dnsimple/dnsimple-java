package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;



public class GetZoneRecordResponse extends ApiResponse {

  private ZoneRecord data;

    public ZoneRecord getData() {
        return data;
    }
}

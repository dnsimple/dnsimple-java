package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;

public class CreateZoneRecordResponse extends ApiResponse {
    private ZoneRecord data;

    public ZoneRecord getData() {
        return data;
    }
}

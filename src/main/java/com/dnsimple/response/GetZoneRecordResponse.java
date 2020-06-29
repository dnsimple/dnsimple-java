package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;

public class GetZoneRecordResponse extends ApiResponse {
    private final ZoneRecord data;

    public GetZoneRecordResponse() {
        data = null;
    }

    public GetZoneRecordResponse(ZoneRecord data) {
        this.data = data;
    }

    public ZoneRecord getData() {
        return data;
    }
}

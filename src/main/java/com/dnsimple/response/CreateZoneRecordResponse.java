package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;

public class CreateZoneRecordResponse extends ApiResponse {
    private final ZoneRecord data;

    public CreateZoneRecordResponse() {
        data = null;
    }

    public CreateZoneRecordResponse(ZoneRecord data) {
        this.data = data;
    }

    public ZoneRecord getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;

public class UpdateZoneRecordResponse extends ApiResponse {
    private final ZoneRecord data;

    public UpdateZoneRecordResponse() {
        data = null;
    }

    public UpdateZoneRecordResponse(ZoneRecord data) {
        this.data = data;
    }

    public ZoneRecord getData() {
        return data;
    }
}

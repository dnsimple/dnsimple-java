package com.dnsimple.response;

import com.dnsimple.data.Zone;

public class GetZoneResponse extends ApiResponse {
    private final Zone data;

    public GetZoneResponse() {
        data = null;
    }

    public GetZoneResponse(Zone data) {
        this.data = data;
    }

    public Zone getData() {
        return data;
    }
}

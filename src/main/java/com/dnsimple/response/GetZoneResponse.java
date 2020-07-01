package com.dnsimple.response;

import com.dnsimple.data.Zone;

public class GetZoneResponse extends ApiResponse {
    private Zone data;

    public Zone getData() {
        return data;
    }
}

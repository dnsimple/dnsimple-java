package com.dnsimple.response;

import com.dnsimple.data.ZoneFile;

public class GetZoneFileResponse extends ApiResponse {
    private final ZoneFile data;

    public GetZoneFileResponse() {
        data = null;
    }

    public GetZoneFileResponse(ZoneFile data) {
        this.data = data;
    }

    public ZoneFile getData() {
        return data;
    }
}

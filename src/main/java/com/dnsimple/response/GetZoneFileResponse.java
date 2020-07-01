package com.dnsimple.response;

import com.dnsimple.data.ZoneFile;

public class GetZoneFileResponse extends ApiResponse {
    private ZoneFile data;

    public ZoneFile getData() {
        return data;
    }
}

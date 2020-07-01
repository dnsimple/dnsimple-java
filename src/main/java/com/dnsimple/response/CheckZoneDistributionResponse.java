package com.dnsimple.response;

import com.dnsimple.data.ZoneDistribution;

public class CheckZoneDistributionResponse extends ApiResponse {
    private ZoneDistribution data;

    public ZoneDistribution getData() {
        return data;
    }
}

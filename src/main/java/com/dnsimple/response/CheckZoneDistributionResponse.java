package com.dnsimple.response;

import com.dnsimple.data.ZoneDistribution;

public class CheckZoneDistributionResponse extends ApiResponse {
    private final ZoneDistribution data;

    public CheckZoneDistributionResponse() {
        data = null;
    }

    public CheckZoneDistributionResponse(ZoneDistribution data) {
        this.data = data;
    }

    public ZoneDistribution getData() {
        return data;
    }
}

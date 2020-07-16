package com.dnsimple.data;

public class ZoneDistribution {
    private final Boolean distributed;

    public ZoneDistribution(boolean distributed) {
        this.distributed = distributed;
    }

    public boolean isDistributed() {
        return distributed;
    }
}

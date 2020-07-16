package com.dnsimple.data;

public class Dnssec {
    private final Boolean enabled;

    public Dnssec(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return enabled;
    }
}

package com.dnsimple.data;

public class DomainAvailability {
    private String domain;
    private Boolean available;
    private Boolean premium;

    public String getDomainName() {
        return domain;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Boolean getPremium() {
        return premium;
    }
}

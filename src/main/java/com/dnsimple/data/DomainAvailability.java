package com.dnsimple.data;

public class DomainAvailability {
    private final String domain;
    private final Boolean available;
    private final Boolean premium;

    public DomainAvailability(String domain, Boolean available, Boolean premium) {
        this.domain = domain;
        this.available = available;
        this.premium = premium;
    }

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

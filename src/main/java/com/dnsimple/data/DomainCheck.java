package com.dnsimple.data;

public class DomainCheck {
    private final String domain;
    private final Boolean available;
    private final Boolean premium;

    public DomainCheck(String domain, Boolean available, Boolean premium) {
        this.domain = domain;
        this.available = available;
        this.premium = premium;
    }

    public String getDomainName() {
        return domain;
    }

    public Boolean isAvailable() {
        return available;
    }

    public Boolean isPremium() {
        return premium;
    }
}

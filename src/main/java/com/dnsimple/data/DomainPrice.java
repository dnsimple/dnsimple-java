package com.dnsimple.data;

public class DomainPrice {
    private final String domain;
    private final boolean premium;
    private final double registrationPrice;
    private final double renewalPrice;
    private final double transferPrice;

    public DomainPrice(String domain, boolean premium, double registrationPrice, double renewalPrice, double transferPrice) {
        this.domain = domain;
        this.premium = premium;
        this.registrationPrice = registrationPrice;
        this.renewalPrice = renewalPrice;
        this.transferPrice = transferPrice;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isPremium() {
        return premium;
    }

    public double getRegistrationPrice() {
        return registrationPrice;
    }

    public double getRenewalPrice() {
        return renewalPrice;
    }

    public double getTransferPrice() {
        return transferPrice;
    }
}

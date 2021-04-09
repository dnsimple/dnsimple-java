package com.dnsimple.data;

public class DomainPrice {
    private final String domain;
    private final Boolean premium;
    private final Float registrationPrice;
    private final Float renewalPrice;
    private final Float transferPrice;

    public DomainPrice(String domain, Boolean premium, Float registrationPrice, Float renewalPrice, Float transferPrice) {
        this.domain = domain;
        this.premium = premium;
        this.registrationPrice = registrationPrice;
        this.renewalPrice = renewalPrice;
        this.transferPrice = transferPrice;
    }

    public String getDomain() {
        return domain;
    }

    public Boolean isPremium() {
        return premium;
    }

    public Float getRegistrationPrice() {
        return registrationPrice;
    }

    public Float getRenewalPrice() {
        return renewalPrice;
    }

    public Float getTransferPrice() {
        return transferPrice;
    }
}

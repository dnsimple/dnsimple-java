package com.dnsimple.data;

public class DomainPremiumPriceCheck {
    private final String premiumPrice;
    private final String action;

    public DomainPremiumPriceCheck(String premiumPrice, String action) {
        this.premiumPrice = premiumPrice;
        this.action = action;
    }

    public String getPremiumPrice() {
        return premiumPrice;
    }

    public String getAction() {
        return action;
    }
}

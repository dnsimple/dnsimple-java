package com.dnsimple.request;

public class RestoreOptions {
    private final String premiumPrice;

    private RestoreOptions(String premiumPrice) {
        this.premiumPrice = premiumPrice;
    }

    public static RestoreOptions empty() {
        return new RestoreOptions(null);
    }

    /**
     * Required as confirmation of the price, only if the domain is premium.
     */
    public RestoreOptions premiumPrice(String premiumPrice) {
        return new RestoreOptions(premiumPrice);
    }
}

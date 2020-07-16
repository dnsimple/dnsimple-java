package com.dnsimple.request;

public class RenewOptions {
    private final Integer period;
    private final String premiumPrice;

    private RenewOptions(Integer period, String premiumPrice) {
        this.period = period;
        this.premiumPrice = premiumPrice;
    }

    public static RenewOptions empty() {
        return new RenewOptions(null, null);
    }

    /**
     * Set the number of years for the renewal. Unless specified it will default to whatever value is set for the TLD.
     */
    public RenewOptions period(int period) {
        return new RenewOptions(period, premiumPrice);
    }

    /**
     * Required as confirmation of the price, only if the domain is premium.
     */
    public RenewOptions premiumPrice(String premiumPrice) {
        return new RenewOptions(period, premiumPrice);
    }
}

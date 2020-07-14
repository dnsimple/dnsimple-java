package com.dnsimple.data;

public class Tld {
    private final String tld;
    private final Integer tldType;
    private final Boolean whoisPrivacy;
    private final Boolean autoRenewOnly;
    private final Integer minimumRegistration;
    private final Boolean registrationEnabled;
    private final Boolean renewalEnabled;
    private final Boolean transferEnabled;

    public Tld(String tld, Integer tldType, Boolean whoisPrivacy, Boolean autoRenewOnly, Integer minimumRegistration, Boolean registrationEnabled, Boolean renewalEnabled, Boolean transferEnabled) {
        this.tld = tld;
        this.tldType = tldType;
        this.whoisPrivacy = whoisPrivacy;
        this.autoRenewOnly = autoRenewOnly;
        this.minimumRegistration = minimumRegistration;
        this.registrationEnabled = registrationEnabled;
        this.renewalEnabled = renewalEnabled;
        this.transferEnabled = transferEnabled;
    }

    public String getTld() {
        return tld;
    }

    public Integer getTldType() {
        return tldType;
    }

    public Boolean supportsWhoisPrivacy() {
        return whoisPrivacy;
    }

    public Boolean isAutorenewOnly() {
        return autoRenewOnly;
    }

    public Integer getMinimumRegistration() {
        return minimumRegistration;
    }

    public Boolean isRegistrationEnabled() {
        return registrationEnabled;
    }

    public Boolean isRenewalEnabled() {
        return renewalEnabled;
    }

    public Boolean isTransferEnabled() {
        return transferEnabled;
    }
}

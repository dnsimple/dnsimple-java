package com.dnsimple.data;

public class Tld {
    private final String tld;
    private final Integer tldType;
    private final Boolean whoisPrivacy;
    private final Boolean autorenewOnly;
    private final Boolean idn;

    public Tld(String tld, Integer tldType, Boolean whoisPrivacy, Boolean autorenewOnly, Boolean idn) {
        this.tld = tld;
        this.tldType = tldType;
        this.whoisPrivacy = whoisPrivacy;
        this.autorenewOnly = autorenewOnly;
        this.idn = idn;
    }

    public String getTld() {
        return tld;
    }

    public Integer getTldType() {
        return tldType;
    }

    public Boolean getWhoisPrivacy() {
        return whoisPrivacy;
    }

    public Boolean getAutorenewOnly() {
        return autorenewOnly;
    }

    public Boolean getIdn() {
        return idn;
    }
}

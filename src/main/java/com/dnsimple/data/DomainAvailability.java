package com.dnsimple.data;

import com.google.gson.annotations.SerializedName;

public class DomainAvailability {
    @SerializedName("domain")
    private String domainName;
    private Boolean available;
    private Boolean premium;

    public String getDomainName() {
        return domainName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Boolean getPremium() {
        return premium;
    }
}

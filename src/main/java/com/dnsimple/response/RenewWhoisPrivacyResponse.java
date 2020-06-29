package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacyRenewal;

public class RenewWhoisPrivacyResponse extends ApiResponse {
    private final WhoisPrivacyRenewal data;

    public RenewWhoisPrivacyResponse() {
        data = null;
    }

    public RenewWhoisPrivacyResponse(WhoisPrivacyRenewal data) {
        this.data = data;
    }

    public WhoisPrivacyRenewal getData() {
        return data;
    }
}

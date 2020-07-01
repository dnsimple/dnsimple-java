package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacyRenewal;

public class RenewWhoisPrivacyResponse extends ApiResponse {
    private WhoisPrivacyRenewal data;

    public WhoisPrivacyRenewal getData() {
        return data;
    }
}

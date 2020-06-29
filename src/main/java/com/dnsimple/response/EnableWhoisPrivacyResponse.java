package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacy;

public class EnableWhoisPrivacyResponse extends ApiResponse {
    private final WhoisPrivacy data;

    public EnableWhoisPrivacyResponse() {
        data = null;
    }

    public EnableWhoisPrivacyResponse(WhoisPrivacy data) {
        this.data = data;
    }

    public WhoisPrivacy getData() {
        return data;
    }
}

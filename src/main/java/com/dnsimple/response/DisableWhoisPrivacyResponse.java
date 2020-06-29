package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacy;

public class DisableWhoisPrivacyResponse extends ApiResponse {
    private final WhoisPrivacy data;

    public DisableWhoisPrivacyResponse() {
        data = null;
    }

    public DisableWhoisPrivacyResponse(WhoisPrivacy data) {
        this.data = data;
    }

    public WhoisPrivacy getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacy;

public class GetWhoisPrivacyResponse extends ApiResponse {
    private final WhoisPrivacy data;

    public GetWhoisPrivacyResponse() {
        data = null;
    }

    public GetWhoisPrivacyResponse(WhoisPrivacy data) {
        this.data = data;
    }

    public WhoisPrivacy getData() {
        return data;
    }
}

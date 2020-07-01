package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacy;

public class EnableWhoisPrivacyResponse extends ApiResponse {
    private WhoisPrivacy data;

    public WhoisPrivacy getData() {
        return data;
    }
}

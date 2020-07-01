package com.dnsimple.response;

import com.dnsimple.data.WhoisPrivacy;



public class DisableWhoisPrivacyResponse extends ApiResponse {

  private WhoisPrivacy data;

    public WhoisPrivacy getData() {
        return data;
    }
}

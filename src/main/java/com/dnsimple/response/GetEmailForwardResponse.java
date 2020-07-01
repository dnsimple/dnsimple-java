package com.dnsimple.response;

import com.dnsimple.data.EmailForward;

public class GetEmailForwardResponse extends ApiResponse {
    private EmailForward data;

    public EmailForward getData() {
        return data;
    }
}

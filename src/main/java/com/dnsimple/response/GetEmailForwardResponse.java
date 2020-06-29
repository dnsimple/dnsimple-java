package com.dnsimple.response;

import com.dnsimple.data.EmailForward;

public class GetEmailForwardResponse extends ApiResponse {
    private final EmailForward data;

    public GetEmailForwardResponse() {
        data = null;
    }

    public GetEmailForwardResponse(EmailForward data) {
        this.data = data;
    }

    public EmailForward getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.EmailForward;

public class CreateEmailForwardResponse extends ApiResponse {
    private final EmailForward data;

    public CreateEmailForwardResponse() {
        data = null;
    }

    public CreateEmailForwardResponse(EmailForward data) {
        this.data = data;
    }

    public EmailForward getData() {
        return data;
    }
}

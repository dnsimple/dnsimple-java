package com.dnsimple.response;

import com.dnsimple.data.Webhook;

public class CreateWebhookResponse extends ApiResponse {
    private final Webhook data;

    public CreateWebhookResponse() {
        data = null;
    }

    public CreateWebhookResponse(Webhook data) {
        this.data = data;
    }

    public Webhook getData() {
        return data;
    }
}

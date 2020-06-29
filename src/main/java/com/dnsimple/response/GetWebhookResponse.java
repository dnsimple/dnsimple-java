package com.dnsimple.response;

import com.dnsimple.data.Webhook;

public class GetWebhookResponse extends ApiResponse {
    private final Webhook data;

    public GetWebhookResponse() {
        data = null;
    }

    public GetWebhookResponse(Webhook data) {
        this.data = data;
    }

    public Webhook getData() {
        return data;
    }
}

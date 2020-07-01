package com.dnsimple.response;

import com.dnsimple.data.Webhook;



public class GetWebhookResponse extends ApiResponse {

  private Webhook data;

    public Webhook getData() {
        return data;
    }
}

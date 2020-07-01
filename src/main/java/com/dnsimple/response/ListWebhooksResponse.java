package com.dnsimple.response;

import com.dnsimple.data.Webhook;

import java.util.List;

public class ListWebhooksResponse extends ApiResponse {
    private List<Webhook> data;

    public List<Webhook> getData() {
        return data;
    }
}

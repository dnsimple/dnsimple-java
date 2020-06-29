package com.dnsimple.response;

import com.dnsimple.data.Webhook;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListWebhooksResponse extends ApiResponse {
    private final List<Webhook> data;

    public ListWebhooksResponse() {
        data = emptyList();
    }

    public ListWebhooksResponse(List<Webhook> data) {
        this.data = data;
    }

    public List<Webhook> getData() {
        return data;
    }
}

package com.dnsimple.data;

public class Webhook {
    private final Long id;
    private final String url;

    public Webhook(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}

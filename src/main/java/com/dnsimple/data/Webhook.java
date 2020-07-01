package com.dnsimple.data;

public class Webhook {
    private final Integer id;
    private final String url;

    public Webhook(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}

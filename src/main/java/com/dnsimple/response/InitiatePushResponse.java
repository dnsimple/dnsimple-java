package com.dnsimple.response;

import com.dnsimple.data.Push;

public class InitiatePushResponse extends ApiResponse {
    private final Push data;

    public InitiatePushResponse() {
        data = null;
    }

    public InitiatePushResponse(Push data) {
        this.data = data;
    }

    public Push getData() {
        return data;
    }
}

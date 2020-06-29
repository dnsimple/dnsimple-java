package com.dnsimple.response;

import com.dnsimple.data.NameServer;

import java.util.List;

import static java.util.Collections.emptyList;

public class EnableVanityNameServersResponse extends ApiResponse {
    private final List<NameServer> data;

    public EnableVanityNameServersResponse() {
        data = emptyList();
    }

    public EnableVanityNameServersResponse(List<NameServer> data) {
        this.data = data;
    }

    public List<NameServer> getData() {
        return data;
    }
}

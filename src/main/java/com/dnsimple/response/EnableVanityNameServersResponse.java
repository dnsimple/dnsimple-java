package com.dnsimple.response;

import com.dnsimple.data.NameServer;

import java.util.List;

public class EnableVanityNameServersResponse extends ApiResponse {
    private List<NameServer> data;

    public List<NameServer> getData() {
        return data;
    }
}

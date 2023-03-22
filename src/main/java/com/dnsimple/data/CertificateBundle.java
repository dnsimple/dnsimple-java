package com.dnsimple.data;

import java.util.List;

public class CertificateBundle {
    private final String server;
    private final String root;
    private final List<String> chain;

    public CertificateBundle(String server, String root, List<String> chain) {
        this.server = server;
        this.root = root;
        this.chain = chain;
    }

    public String getServer() {
        return server;
    }

    public String getRoot() {
        return root;
    }

    public List<String> getChain() {
        return chain;
    }
}

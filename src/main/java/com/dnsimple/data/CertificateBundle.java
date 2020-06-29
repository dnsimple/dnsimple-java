package com.dnsimple.data;

import java.util.List;

public class CertificateBundle {
    private final String privateKey;
    private final String server;
    private final String root;
    private final List<String> chain;

    public CertificateBundle(String privateKey, String server, String root, List<String> chain) {
        this.privateKey = privateKey;
        this.server = server;
        this.root = root;
        this.chain = chain;
    }

    public String getPrivateKey() {
        return privateKey;
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

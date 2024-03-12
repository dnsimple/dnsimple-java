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

    public String getServerCertificate() {
        return server;
    }

    public String getRootCertificate() {
        return root;
    }

    public List<String> getIntermediateCertificates() {
        return chain;
    }
}

package com.dnsimple.data;

import java.util.List;

public class CertificateBundle {
    private String privateKey;
    private String server;
    private String root;
    private List<String> chain;

    public String getPrivateKey() {
        return privateKey;
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

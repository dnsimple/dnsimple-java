package com.dnsimple.data;

public class CertificatePrivateKey {
    private final String privateKey;

    public CertificatePrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}

package com.dnsimple.data;

public class DelegationSignerRecord {
    private Integer id;
    private Integer domainId;
    private String algorithm;
    private String digest;
    private String digestType;
    private String keytag;
    private String createdAt;
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getDigest() {
        return digest;
    }

    public String getDigestType() {
        return digestType;
    }

    public String getKeytag() {
        return keytag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

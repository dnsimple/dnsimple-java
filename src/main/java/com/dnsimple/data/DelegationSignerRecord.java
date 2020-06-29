package com.dnsimple.data;

public class DelegationSignerRecord {
    private final Integer id;
    private final Integer domainId;
    private final String algorithm;
    private final String digest;
    private final String digestType;
    private final String keytag;
    private final String createdAt;
    private final String updatedAt;

    public DelegationSignerRecord(Integer id, Integer domainId, String algorithm, String digest, String digestType, String keytag, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.algorithm = algorithm;
        this.digest = digest;
        this.digestType = digestType;
        this.keytag = keytag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

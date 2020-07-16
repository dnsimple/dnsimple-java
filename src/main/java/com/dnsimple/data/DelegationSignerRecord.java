package com.dnsimple.data;

import java.time.OffsetDateTime;

public class DelegationSignerRecord {
    private final Long id;
    private final Long domainId;
    private final String algorithm;
    private final String digest;
    private final String digestType;
    private final String keytag;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public DelegationSignerRecord(Long id, Long domainId, String algorithm, String digest, String digestType, String keytag, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.algorithm = algorithm;
        this.digest = digest;
        this.digestType = digestType;
        this.keytag = keytag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

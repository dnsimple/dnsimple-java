package com.dnsimple.data;

import java.time.OffsetDateTime;

public class CertificateRenewal {
    private final Long id;
    private final Long oldCertificateId;
    private final Long newCertificateId;
    private final String state;
    private final Boolean autoRenew;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public CertificateRenewal(Long id, Long oldCertificateId, Long newCertificateId, String state, Boolean autoRenew, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.oldCertificateId = oldCertificateId;
        this.newCertificateId = newCertificateId;
        this.state = state;
        this.autoRenew = autoRenew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getOldCertificateId() {
        return oldCertificateId;
    }

    public Long getNewCertificateId() {
        return newCertificateId;
    }

    public String getState() {
        return state;
    }

    public Boolean hasAutoRenew() {
        return autoRenew;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

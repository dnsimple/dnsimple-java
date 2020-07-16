package com.dnsimple.data;

import java.time.OffsetDateTime;

public class CertificatePurchase {
    private final Long id;
    private final Long certificateId;
    private final String state;
    private final Boolean autoRenew;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public CertificatePurchase(Long id, Long certificateId, String state, Boolean autoRenew, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.certificateId = certificateId;
        this.state = state;
        this.autoRenew = autoRenew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCertificateId() {
        return certificateId;
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

package com.dnsimple.data;

public class CertificatePurchase {
    private final Integer id;
    private final Integer certificateId;
    private final String createdAt;
    private final String updatedAt;

    public CertificatePurchase(Integer id, Integer certificateId, String createdAt, String updatedAt) {
        this.id = id;
        this.certificateId = certificateId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

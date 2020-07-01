package com.dnsimple.data;

public class CertificateRenewal {
    private final Integer id;
    private final Integer oldCertificateId;
    private final Integer newCertificateId;
    private final String createdAt;
    private final String updatedAt;

    public CertificateRenewal(Integer id, Integer oldCertificateId, Integer newCertificateId, String createdAt, String updatedAt) {
        this.id = id;
        this.oldCertificateId = oldCertificateId;
        this.newCertificateId = newCertificateId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOldCertificateId() {
        return oldCertificateId;
    }

    public Integer getNewCertificateId() {
        return newCertificateId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

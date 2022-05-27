package com.dnsimple.data;

import java.time.OffsetDateTime;
import java.util.List;

public class Certificate {
    private final Long id;
    private final Long domainId;
    @Deprecated
    private final Long contactId;
    private final String commonName;
    private final List<String> alternateNames;
    private final Integer years;
    private final String state;
    private final String authorityIdentifier;
    private final Boolean autoRenew;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;
    private final OffsetDateTime expiresAt;
    private final String csr;

    public Certificate(Long id, Long domainId, String commonName, List<String> alternateNames, Integer years, String state, String authorityIdentifier, Boolean autoRenew, OffsetDateTime createdAt, OffsetDateTime updatedAt, OffsetDateTime expiresAt, String csr) {
        this.id = id;
        this.domainId = domainId;
        this.contactId = null;
        this.commonName = commonName;
        this.alternateNames = alternateNames;
        this.years = years;
        this.state = state;
        this.authorityIdentifier = authorityIdentifier;
        this.autoRenew = autoRenew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.csr = csr;
    }

    /**
     * @deprecated Please, use a constructor without deprecated arguments
     */
    @Deprecated
    public Certificate(Long id, Long domainId, @Deprecated Long contactId, String commonName, List<String> alternateNames, Integer years, String state, String authorityIdentifier, Boolean autoRenew, OffsetDateTime createdAt, OffsetDateTime updatedAt, OffsetDateTime expiresAt, String csr) {
        this.id = id;
        this.domainId = domainId;
        this.contactId = contactId;
        this.commonName = commonName;
        this.alternateNames = alternateNames;
        this.years = years;
        this.state = state;
        this.authorityIdentifier = authorityIdentifier;
        this.autoRenew = autoRenew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.csr = csr;
    }

    public Long getId() {
        return id;
    }

    public Long getDomainId() {
        return domainId;
    }

    @Deprecated
    public Long getContactId() {
        return contactId;
    }

    public String getCommonName() {
        return commonName;
    }

    public List<String> getAlternateNames() {
        return alternateNames;
    }

    public Integer getYears() {
        return years;
    }

    public String getState() {
        return state;
    }

    public String getAuthorityIdentifier() {
        return authorityIdentifier;
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

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public String getCertificateRequest() {
        return csr;
    }
}

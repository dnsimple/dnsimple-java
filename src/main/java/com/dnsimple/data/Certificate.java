package com.dnsimple.data;

public class Certificate {
    private final Integer id;
    private final Integer domainId;
    private final String name;
    private final String commonName;
    private final Integer years;
    private final String csr;
    private final String state;
    private final String authorityIdentifier;
    private final String createdAt;
    private final String updatedAt;
    private final String expiresAt;

    public Certificate(Integer id, Integer domainId, String name, String commonName, Integer years, String csr, String state, String authorityIdentifier, String createdAt, String updatedAt, String expiresAt) {
        this.id = id;
        this.domainId = domainId;
        this.name = name;
        this.commonName = commonName;
        this.years = years;
        this.csr = csr;
        this.state = state;
        this.authorityIdentifier = authorityIdentifier;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getName() {
        return name;
    }

    public String getCommonName() {
        return commonName;
    }

    public Integer getYears() {
        return years;
    }

    public String getCsr() {
        return csr;
    }

    public String getState() {
        return state;
    }

    public String getAuthorityIdentifier() {
        return authorityIdentifier;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * @return the expiration date in ISO8601 pattern.
     * @deprecated use {@link Domain#getExpiresAt()} instead.
     */
    @Deprecated
    public String getExpiresOn() {
        return expiresAt != null ? expiresAt.substring(0, 10) : null;
    }
}

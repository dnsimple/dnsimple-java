package com.dnsimple.data;

public class Certificate {
    private Integer id;
    private Integer domainId;
    private String name;
    private String commonName;
    private Integer years;
    private String csr;
    private String state;
    private String authorityIdentifier;
    private String createdAt;
    private String updatedAt;
    private String expiresAt;

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
     * @return the expiration date in {@link java.time.format.DateTimeFormatter.DateTimeFormatter#ISO_DATE} pattern.
     * @deprecated use {@link Domain#getExpiresAt()} instead.
     */
    @Deprecated
    public String getExpiresOn() {
        return expiresAt != null ? expiresAt.substring(0, 10) : null;
    }
}

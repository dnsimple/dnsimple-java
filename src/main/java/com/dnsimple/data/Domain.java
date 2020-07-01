package com.dnsimple.data;

public class Domain {
    private Integer id;
    private Integer accountId;
    private Integer registrantId;
    private String name;
    private String unicodeName;
    private String state;
    private boolean autoRenew;
    private boolean privateWhois;
    private String expiresAt;
    private String createdAt;
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getRegistrantId() {
        return registrantId;
    }

    public String getName() {
        return name;
    }

    public String getUnicodeName() {
        return unicodeName;
    }

    public String getState() {
        return state;
    }

    public boolean getAutoRenew() {
        return autoRenew;
    }

    public boolean getPrivateWhois() {
        return privateWhois;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * @return the expiration date in {@link DateTimeFormatter#ISO_DATE} pattern.
     * @deprecated use {@link Domain#getExpiresAt()} instead.
     */
    @Deprecated
    public String getExpiresOn() {
        return expiresAt != null ? expiresAt.substring(0, 10) : null;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

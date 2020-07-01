package com.dnsimple.data;

public class Domain {
    private final Integer id;
    private final Integer accountId;
    private final Integer registrantId;
    private final String name;
    private final String unicodeName;
    private final String state;
    private final boolean autoRenew;
    private final boolean privateWhois;
    private final String expiresAt;
    private final String createdAt;
    private final String updatedAt;

    public Domain(Integer id, Integer accountId, Integer registrantId, String name, String unicodeName, String state, boolean autoRenew, boolean privateWhois, String expiresAt, String createdAt, String updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.registrantId = registrantId;
        this.name = name;
        this.unicodeName = unicodeName;
        this.state = state;
        this.autoRenew = autoRenew;
        this.privateWhois = privateWhois;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

package com.dnsimple.data;

public class Zone {
    private final Integer id;
    private final Integer accountId;
    private final String name;
    private final Boolean reverse;
    private final String createdAt;
    private final String updatedAt;

    public Zone(Integer id, Integer accountId, String name, Boolean reverse, String createdAt, String updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.reverse = reverse;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

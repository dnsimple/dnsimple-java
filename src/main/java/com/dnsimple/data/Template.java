package com.dnsimple.data;

public class Template {
    private final Integer id;
    private final Integer accountId;
    private final String name;
    private final String sid;
    private final String description;
    private final String createdAt;
    private final String updatedAt;

    public Template(Integer id, Integer accountId, String name, String sid, String description, String createdAt, String updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.sid = sid;
        this.description = description;
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

    public String getShortName() {
        return sid;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

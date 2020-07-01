package com.dnsimple.data;

import com.google.gson.annotations.SerializedName;

public class Template {
    private Integer id;
    private Integer accountId;
    private String name;
    @SerializedName("sid")
    private String shortName;
    private String description;
    private String createdAt;
    private String updatedAt;

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
        return shortName;
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

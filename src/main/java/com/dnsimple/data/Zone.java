package com.dnsimple.data;

public class Zone {
    private Integer id;
    private Integer accountId;
    private String name;
    private Boolean reverse;
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

package com.dnsimple.data;

public class Push {
    private Integer id;
    private Integer domainId;
    private Integer contactId;
    private Integer accountId;
    private String acceptedAt;
    private String createdAt;
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

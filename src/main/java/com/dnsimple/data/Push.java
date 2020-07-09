package com.dnsimple.data;

public class Push {
    private final Integer id;
    private final Integer domainId;
    private final Integer contactId;
    private final Integer accountId;
    private final String acceptedAt;
    private final String createdAt;
    private final String updatedAt;

    public Push(Integer id, Integer domainId, Integer contactId, Integer accountId, String acceptedAt, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.contactId = contactId;
        this.accountId = accountId;
        this.acceptedAt = acceptedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

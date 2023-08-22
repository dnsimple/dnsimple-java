package com.dnsimple.data;

import java.util.Map;

public class RegistrantChange {
    private final Long id;
    private final Long type;
    private final Long accountId;
    private final Long contactId;
    private final Long domainId;
    private final String state;
    private final Map<String, String> extendedAttributes;
    private final Boolean registryOwnerChange;
    private final String irtLockLiftedBy;
    private final String createdAt;
    private final String updatedAt;

    public RegistrantChange(Long id, Long type, Long accountId, Long contactId, Long domainId, String state, Map<String, String> extendedAttributes, Boolean registryOwnerChange, String irtLockLiftedBy, String createdAt, String updatedAt) {
        this.id = id;
        this.type = type;
        this.accountId = accountId;
        this.contactId = contactId;
        this.domainId = domainId;
        this.state = state;
        this.extendedAttributes = extendedAttributes;
        this.registryOwnerChange = registryOwnerChange;
        this.irtLockLiftedBy = irtLockLiftedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getType() {
        return type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getContactId() {
        return contactId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getState() {
        return state;
    }

    public Map<String, String> getExtendedAttributes() {
        return extendedAttributes;
    }

    public Boolean getRegistryOwnerChange() {
        return registryOwnerChange;
    }

    public String getIrtLockLiftedBy() {
        return irtLockLiftedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

package com.dnsimple.data;

import java.util.List;

public class RegistrantChangeCheck {
    private final Long contactId;
    private final Long domainId;
    private final List<TldExtendedAttribute> extendedAttributes;
    private final Boolean registryOwnerChange;

    public RegistrantChangeCheck(Long contactId, Long domainId, List<TldExtendedAttribute> extendedAttributes, Boolean registryOwnerChange) {
        this.contactId = contactId;
        this.domainId = domainId;
        this.extendedAttributes = extendedAttributes;
        this.registryOwnerChange = registryOwnerChange;
    }

    public Long getContactId() {
        return contactId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public List<TldExtendedAttribute> getExtendedAttributes() {
        return extendedAttributes;
    }

    public Boolean getRegistryOwnerChange() {
        return registryOwnerChange;
    }
}

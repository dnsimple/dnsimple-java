package com.dnsimple.data;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrantChangeCheck that = (RegistrantChangeCheck) o;
        return Objects.equals(contactId, that.contactId) && Objects.equals(domainId, that.domainId) && Objects.equals(extendedAttributes, that.extendedAttributes) && Objects.equals(registryOwnerChange, that.registryOwnerChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, domainId, extendedAttributes, registryOwnerChange);
    }
}

package com.dnsimple.request;

import java.util.Map;

public class CreateRegistrantChangeInput {
    private final String domainId;
    private final String contactId;
    private final Map<String, String> extendedAttributes;

    public CreateRegistrantChangeInput(String domainId, String contactId, Map<String, String> extendedAttributes) {
        this.domainId = domainId;
        this.contactId = contactId;
        this.extendedAttributes = extendedAttributes;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getContactId() {
        return contactId;
    }

    public Map<String, String> getExtendedAttributes() {
        return extendedAttributes;
    }
}

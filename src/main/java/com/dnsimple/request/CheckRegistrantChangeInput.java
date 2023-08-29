package com.dnsimple.request;

public class CheckRegistrantChangeInput {
    private final String domainId;
    private final String contactId;

    public CheckRegistrantChangeInput(String domainId, String contactId) {
        this.domainId = domainId;
        this.contactId = contactId;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getContactId() {
        return contactId;
    }
}

package com.dnsimple.request;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class CertificatePurchaseOptions {
    private final Long contactId;
    private final boolean autoRenew;
    private final String name;
    private final List<String> alternateNames;

    private CertificatePurchaseOptions(Long contactId, boolean autoRenew, String name, List<String> alternateNames) {
        this.contactId = contactId;
        this.autoRenew = autoRenew;
        this.name = name;
        this.alternateNames = alternateNames;
    }

    /**
     * @param contactId The ID of an existing contact in your account.
     */
    public static CertificatePurchaseOptions of(Number contactId) {
        return new CertificatePurchaseOptions(contactId.longValue(), false, "www", emptyList());
    }

    /**
     * Enable auto-renew for the certificate
     */
    public CertificatePurchaseOptions autoRenew() {
        return new CertificatePurchaseOptions(contactId, true, name, alternateNames);
    }

    /**
     * Set a name for the certificate
     */
    public CertificatePurchaseOptions name(String name) {
        return new CertificatePurchaseOptions(contactId, autoRenew, name, alternateNames);
    }

    /**
     * Set alternate names for the certificate
     */
    public CertificatePurchaseOptions alternateNames(String... names) {
        return new CertificatePurchaseOptions(contactId, autoRenew, name, Arrays.asList(names));
    }
}

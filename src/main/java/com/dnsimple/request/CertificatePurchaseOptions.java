package com.dnsimple.request;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class CertificatePurchaseOptions {
    private final boolean autoRenew;
    private final String name;
    private final List<String> alternateNames;

    private CertificatePurchaseOptions(boolean autoRenew, String name, List<String> alternateNames) {
        this.autoRenew = autoRenew;
        this.name = name;
        this.alternateNames = alternateNames;
    }

    /**
     * @deprecated Please, use a constructor without deprecated arguments
     */
    @Deprecated
    private CertificatePurchaseOptions(@Deprecated Long contactId, boolean autoRenew, String name, List<String> alternateNames) {
        this.autoRenew = autoRenew;
        this.name = name;
        this.alternateNames = alternateNames;
    }


    /**
     * @param contactId This argument has been deprecated and is ignored in upstream API requests
     * @deprecated Please, use the class constructor instead
     */
    public static CertificatePurchaseOptions of(@Deprecated Number contactId) {
        return new CertificatePurchaseOptions(false, "www", emptyList());
    }

    /**
     * Enable auto-renew for the certificate
     */
    public CertificatePurchaseOptions autoRenew() {
        return new CertificatePurchaseOptions(true, name, alternateNames);
    }

    /**
     * Set a name for the certificate
     */
    public CertificatePurchaseOptions name(String name) {
        return new CertificatePurchaseOptions(autoRenew, name, alternateNames);
    }

    /**
     * Set alternate names for the certificate
     */
    public CertificatePurchaseOptions alternateNames(String... names) {
        return new CertificatePurchaseOptions(autoRenew, name, Arrays.asList(names));
    }
}

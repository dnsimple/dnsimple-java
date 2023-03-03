package com.dnsimple.request;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

public class CertificatePurchaseOptions {
    private final boolean autoRenew;
    private final String name;
    private final List<String> alternateNames;
    private final SignatureAlgorithm signatureAlgorithm;

    private CertificatePurchaseOptions(boolean autoRenew, String name, List<String> alternateNames, SignatureAlgorithm signatureAlgorithm) {
        this.autoRenew = autoRenew;
        this.name = name;
        this.alternateNames = alternateNames;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    /**
     * @param name The name of the certificate to be requested
     */
    public static CertificatePurchaseOptions of(String name) {
        return new CertificatePurchaseOptions(false, name, emptyList(), SignatureAlgorithm.ECDSA);
    }

    /**
     * Enable auto-renew for the certificate
     */
    public CertificatePurchaseOptions autoRenew() {
        return new CertificatePurchaseOptions(true, name, alternateNames, signatureAlgorithm);
    }

    /**
     * Set alternate names for the certificate
     */
    public CertificatePurchaseOptions alternateNames(String... names) {
        return new CertificatePurchaseOptions(autoRenew, name, Arrays.asList(names), signatureAlgorithm);
    }

    /**
     * Set the signature algorithm for the certificate
     */
    public CertificatePurchaseOptions signatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        return new CertificatePurchaseOptions(autoRenew, name, alternateNames, signatureAlgorithm);
    }
}

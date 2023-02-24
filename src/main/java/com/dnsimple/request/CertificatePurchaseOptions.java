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
     * @deprecated Please, use a constructor without deprecated arguments
     */
    @Deprecated
    private CertificatePurchaseOptions(@Deprecated Long contactId, boolean autoRenew, String name, List<String> alternateNames, SignatureAlgorithm signatureAlgorithm) {
        this.autoRenew = autoRenew;
        this.name = name;
        this.alternateNames = alternateNames;
        this.signatureAlgorithm = signatureAlgorithm;
    }


    /**
     * @param contactId This argument has been deprecated and is ignored in upstream API requests
     * @deprecated Please, use the class constructor instead
     */
    @Deprecated
    public static CertificatePurchaseOptions of(@Deprecated Number contactId) {
        return new CertificatePurchaseOptions(false, "www", emptyList(), SignatureAlgorithm.ECDSA);
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
        return new CertificatePurchaseOptions(true, name, alternateNames, SignatureAlgorithm.ECDSA);
    }

    /**
     * @deprecated Please, use the {@link #of(String)} factory method instead
     */
    @Deprecated
    public CertificatePurchaseOptions name(String name) {
        return new CertificatePurchaseOptions(autoRenew, name, alternateNames, SignatureAlgorithm.ECDSA);
    }

    /**
     * Set alternate names for the certificate
     */
    public CertificatePurchaseOptions alternateNames(String... names) {
        return new CertificatePurchaseOptions(autoRenew, name, Arrays.asList(names), SignatureAlgorithm.ECDSA);
    }

    /**
     * Set the signature algorithm for the certificate
     */
    public CertificatePurchaseOptions signatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        return new CertificatePurchaseOptions(autoRenew, name, alternateNames, signatureAlgorithm);
    }
}

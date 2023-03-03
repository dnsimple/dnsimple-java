package com.dnsimple.request;

public class CertificateRenewalPurchaseOptions {
    private final boolean autoRenew;
    private final SignatureAlgorithm signatureAlgorithm;

    private CertificateRenewalPurchaseOptions(boolean autoRenew, SignatureAlgorithm signatureAlgorithm) {
        this.autoRenew = autoRenew;
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public static CertificateRenewalPurchaseOptions empty() {
        return new CertificateRenewalPurchaseOptions(false, SignatureAlgorithm.ECDSA);
    }

    /**
     * Enable auto-renew for the certificate
     */
    public CertificateRenewalPurchaseOptions autoRenew() {
        return new CertificateRenewalPurchaseOptions(true, signatureAlgorithm);
    }

    /**
     * Set the signature algorithm for the certificate
     */
    public CertificateRenewalPurchaseOptions signatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        return new CertificateRenewalPurchaseOptions(autoRenew, signatureAlgorithm);
    }
}

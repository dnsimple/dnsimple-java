package com.dnsimple.request;

public class DSRecordOptions {
    private final String algorithm;
    private final String digest;
    private final String digestType;
    private final String keytag;

    private DSRecordOptions(String algorithm, String digest, String digestType, String keytag) {
        this.algorithm = algorithm;
        this.digest = digest;
        this.digestType = digestType;
        this.keytag = keytag;
    }

    /**
     * For additional information, please see <a href="https://tools.ietf.org/html/rfc4034">https://tools.ietf.org/html/rfc4034</a>.
     *
     * @param algorithm  DNSSEC algorithms defined in <a href="http://www.iana.org/assignments/dns-sec-alg-numbers/dns-sec-alg-numbers.xhtml">http://www.iana.org/assignments/dns-sec-alg-numbers/dns-sec-alg-numbers.xhtml</a>
     * @param digest     The hexidecimal representation of the digest of the corresponding DNSKEY record.
     * @param digestType DNSSEC digest types defined in <a href="http://www.iana.org/assignments/ds-rr-types/ds-rr-types.xhtml">http://www.iana.org/assignments/ds-rr-types/ds-rr-types.xhtml</a>
     * @param keytag     A keytag that references the corresponding DNSKEY record.
     */
    public static DSRecordOptions of(String algorithm, String digest, String digestType, String keytag) {
        return new DSRecordOptions(algorithm, digest, digestType, keytag);
    }
}

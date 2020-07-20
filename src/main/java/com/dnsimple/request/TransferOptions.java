package com.dnsimple.request;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class TransferOptions {
    private final Long registrantId;
    private final String authCode;
    private final Boolean whoisPrivacy;
    private final Boolean autoRenew;
    private final Map<String, String> extendedAttributes;
    private final String premiumPrice;

    private TransferOptions(Long registrantId, String authCode, Boolean whoisPrivacy, Boolean autoRenew, Map<String, String> extendedAttributes, String premiumPrice) {
        this.registrantId = registrantId;
        this.authCode = authCode;
        this.whoisPrivacy = whoisPrivacy;
        this.autoRenew = autoRenew;
        this.extendedAttributes = extendedAttributes;
        this.premiumPrice = premiumPrice;
    }

    /**
     * @param registrantId The ID of an existing contact in your account.
     */
    public static TransferOptions of(Number registrantId) {
        return new TransferOptions(registrantId.longValue(), null, false, true, emptyMap(), null);
    }

    /**
     * Set the authCode required for TLDS that require authorization-based transfer (the vast majority of TLDs).
     */
    public TransferOptions authCode(String authCode) {
        return new TransferOptions(registrantId, authCode, whoisPrivacy, autoRenew, extendedAttributes, premiumPrice);
    }

    /**
     * Enable the whois privacy as part of the transfer. An extra cost may apply.
     */
    public TransferOptions whoisPrivacy() {
        return new TransferOptions(registrantId, authCode, true, autoRenew, extendedAttributes, premiumPrice);
    }

    /**
     * Disable the auto-renewal of the domain.
     */
    public TransferOptions noAutoRenew() {
        return new TransferOptions(registrantId, authCode, whoisPrivacy, false, extendedAttributes, premiumPrice);
    }

    /**
     * Set the extended attributes required for TLDs that require extended attributes.
     * See: <a href="https://developer.dnsimple.com/v2/tlds/#getTldExtendedAttributes">https://developer.dnsimple.com/v2/tlds/#getTldExtendedAttributes</a>
     */
    public TransferOptions extendedAttribute(String name, String value) {
        Map<String, String> newExtendedAttributes = new HashMap<>(extendedAttributes);
        newExtendedAttributes.put(name, value);
        return new TransferOptions(registrantId, authCode, whoisPrivacy, autoRenew, newExtendedAttributes, premiumPrice);
    }

    /**
     * Required as confirmation of the price, only if the domain is premium.
     */
    public TransferOptions premiumPrice(String premiumPrice) {
        return new TransferOptions(registrantId, authCode, whoisPrivacy, autoRenew, extendedAttributes, premiumPrice);
    }
}

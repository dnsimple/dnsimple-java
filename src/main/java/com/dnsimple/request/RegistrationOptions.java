package com.dnsimple.request;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class RegistrationOptions {
    private final Long registrantId;
    private final boolean whoisPrivacy;
    private final boolean autoRenew;
    private final Map<String, String> extendedAttributes;
    private final String premiumPrice;
    private final Boolean trusteeService;

    private RegistrationOptions(
        Long registrantId,
        boolean whoisPrivacy,
        boolean autoRenew,
        Map<String, String> extendedAttributes,
        String premiumPrice,
        Boolean trusteeService
    ) {
        this.registrantId = registrantId;
        this.whoisPrivacy = whoisPrivacy;
        this.autoRenew = autoRenew;
        this.extendedAttributes = extendedAttributes;
        this.premiumPrice = premiumPrice;
        this.trusteeService = trusteeService;
    }

    /**
     * @param registrantId The ID of an existing contact in your account.
     */
    public static RegistrationOptions of(Number registrantId) {
        return new RegistrationOptions(registrantId.longValue(), false, true, emptyMap(), null, null);
    }

    /**
     * Enable the whois privacy as part of the transfer. An extra cost may apply.
     */
    public RegistrationOptions whoisPrivacy() {
        return new RegistrationOptions(registrantId, true, autoRenew, extendedAttributes, premiumPrice, trusteeService);
    }

    /**
     * Disable the auto-renewal of the domain.
     */
    public RegistrationOptions noAutoRenew() {
        return new RegistrationOptions(registrantId, whoisPrivacy, false, extendedAttributes, premiumPrice, trusteeService);
    }

    /**
     * Set the extended attributes required for TLDs that require extended attributes.
     * See: <a href="https://developer.dnsimple.com/v2/tlds/#getTldExtendedAttributes">https://developer.dnsimple.com/v2/tlds/#getTldExtendedAttributes</a>
     */
    public RegistrationOptions extendedAttribute(String name, String value) {
        Map<String, String> newExtendedAttributes = new HashMap<>(extendedAttributes);
        newExtendedAttributes.put(name, value);
        return new RegistrationOptions(registrantId, whoisPrivacy, autoRenew, newExtendedAttributes, premiumPrice, trusteeService);
    }

    /**
     * Required as confirmation of the price, only if the domain is premium.
     */
    public RegistrationOptions premiumPrice(String premiumPrice) {
        return new RegistrationOptions(registrantId, whoisPrivacy, autoRenew, extendedAttributes, premiumPrice, trusteeService);
    }

    /**
     * Enable trustee service as part of the domain registration.
     *
     * <p>This parameter is optional; if you never call this method, the SDK will omit
     * {@code trustee_service} from the JSON request payload.</p>
     */
    public RegistrationOptions trusteeService() {
        return new RegistrationOptions(registrantId, whoisPrivacy, autoRenew, extendedAttributes, premiumPrice, true);
    }
}

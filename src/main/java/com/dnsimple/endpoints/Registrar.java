package com.dnsimple.endpoints;

import com.dnsimple.data.*;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.*;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;

/**
 * Provides access to the DNSimple Registrar API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/registrar">https://developer.dnsimple.com/v2/registrar</a>
 */
public class Registrar {
    private final HttpEndpointClient client;

    public Registrar(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Checks whether a domain is available for registration.
     *
     * @param account    The account ID
     * @param domainName The domain to check
     * @return The check domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#checkDomain">https://developer.dnsimple.com/v2/registrar/#checkDomain</a>
     */
    public SimpleResponse<DomainCheck> checkDomain(Number account, String domainName) {
        return client.simple(GET, account + "/registrar/domains/" + domainName + "/check", ListOptions.empty(), null, DomainCheck.class);
    }

    /**
     * Checks the premium price of a domain for the provided action.
     *
     * @param account    The account ID
     * @param domainName The domain to check
     * @param action     The action to get the price of
     * @return The premium price
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainPremiumPrice">https://developer.dnsimple.com/v2/registrar/#getDomainPremiumPrice</a>
     * @deprecated As of this version 0.9.1, replaced by {@link #getDomainPrices(Number, String)}
     */
    @Deprecated
    public SimpleResponse<DomainPremiumPriceCheck> getDomainPremiumPrice(Number account, String domainName, DomainCheckPremiumPriceAction action) {
        var options = ListOptions.empty().filter("action", action.name().toLowerCase());
        return client.simple(GET, account + "/registrar/domains/" + domainName + "/premium_price", options, action, DomainPremiumPriceCheck.class);
    }

    /**
     * Get prices for registration, transfer, and renewal for a domain.
     *
     * @param account    The account ID
     * @param domainName The domain to check the prices
     *
     * @return the domain prices response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainPrices">https://developer.dnsimple.com/v2/registrar/#getDomainPrices</a>
     */
    public SimpleResponse<DomainPrice> getDomainPrices(Number account, String domainName) {
        return client.simple(GET, account + "/registrar/domains/" + domainName + "/prices", ListOptions.empty(), null, DomainPrice.class);
    }

    /**
     * Get the details of an existing domain registration.
     *
     * @param account            The account ID
     * @param domainName         The domain to check the registration
     * @param domainRegistration The domain registration ID
     *
     * @return the domain registration response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainRegistration">https://developer.dnsimple.com/v2/registrar/#getDomainRegistration</a>
     */
    public SimpleResponse<DomainRegistration> getDomainRegistration(Number account, String domainName, Number domainRegistration) {
        return client.simple(GET, account + "/registrar/domains/" + domainName + "/registrations/" + domainRegistration, ListOptions.empty(), null, DomainRegistration.class);
    }

    /**
     * Get the details of an existing domain renewal.
     *
     * @param account            The account ID
     * @param domainName         The domain to check the renewal
     * @param domainRenewal The domain renewal ID
     *
     * @return the domain renewal response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainRenewal">https://developer.dnsimple.com/v2/registrar/#getDomainRenewal</a>
     */
    public SimpleResponse<DomainRenewal> getDomainRenewal(Number account, String domainName, Number domainRenewal) {
        return client.simple(GET, account + "/registrar/domains/" + domainName + "/renewals/" + domainRenewal, ListOptions.empty(), null, DomainRenewal.class);
    }

    /**
     * Registers a domain.
     *
     * @param account    The account ID
     * @param domainName The domain to register
     * @param options    The options for the domain registration
     * @return The register domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#registerDomain">https://developer.dnsimple.com/v2/registrar/#registerDomain</a>
     */
    public SimpleResponse<DomainRegistration> registerDomain(Number account, String domainName, RegistrationOptions options) {
        return client.simple(POST, account + "/registrar/domains/" + domainName + "/registrations", ListOptions.empty(), options, DomainRegistration.class);
    }

    /**
     * Starts the transfer of a domain to DNSimple.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the transfer
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#transferDomain">https://developer.dnsimple.com/v2/registrar/#transferDomain</a>
     */
    public SimpleResponse<DomainTransfer> transferDomain(Number account, String domain, TransferOptions options) {
        return client.simple(POST, account + "/registrar/domains/" + domain + "/transfers", ListOptions.empty(), options, DomainTransfer.class);
    }

    /**
     * Retrieves the details of an existing domain transfer.
     *
     * @param account          The account ID
     * @param domain           The domain name or ID
     * @param domainTransferId The domain transfer ID
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainTransfer">https://developer.dnsimple.com/v2/registrar/#getDomainTransfer</a>
     */
    public SimpleResponse<DomainTransfer> getDomainTransfer(Number account, String domain, Number domainTransferId) {
        return client.simple(GET, account + "/registrar/domains/" + domain + "/transfers/" + domainTransferId, ListOptions.empty(), null, DomainTransfer.class);
    }

    /**
     * Cancels an in progress domain transfer.
     *
     * @param account          The account ID
     * @param domain           The domain name or ID
     * @param domainTransferId The domain transfer ID
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#cancelDomainTransfer">https://developer.dnsimple.com/v2/registrar/#cancelDomainTransfer</a>
     */
    public SimpleResponse<DomainTransfer> cancelDomainTransfer(Number account, String domain, Number domainTransferId) {
        return client.simple(DELETE, account + "/registrar/domains/" + domain + "/transfers/" + domainTransferId, ListOptions.empty(), null, DomainTransfer.class);
    }

    /**
     * Renews a domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the renewal
     * @return The renew domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#renewDomain">https://developer.dnsimple.com/v2/registrar/#renewDomain</a>
     */
    public SimpleResponse<DomainRenewal> renewDomain(Number account, String domain, RenewOptions options) {
        return client.simple(POST, account + "/registrar/domains/" + domain + "/renewals", ListOptions.empty(), options, DomainRenewal.class);
    }

    /**
     * Authorizes the transfer of a domain out of DNSimple.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The transfer domain out response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#authorizeDomainTransferOut">https://developer.dnsimple.com/v2/registrar/#authorizeDomainTransferOut</a>
     */
    public EmptyResponse authorizeTransferOut(Number account, String domain) {
        return client.empty(POST, account + "/registrar/domains/" + domain + "/authorize_transfer_out", ListOptions.empty(), null);
    }

    /**
     * Enable auto renewal for the domain in the account.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The enable auto renewal response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/auto-renewal/#enable">https://developer.dnsimple.com/v2/registrar/auto-renewal/#enable</a>
     */
    public EmptyResponse enableAutoRenewal(Number account, String domain) {
        return client.empty(PUT, account + "/registrar/domains/" + domain + "/auto_renewal", ListOptions.empty(), null);
    }

    /**
     * Disable auto renewal for the domain in the account.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The disable auto renewal response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/auto-renewal/#disable">https://developer.dnsimple.com/v2/registrar/auto-renewal/#disable</a>
     */
    public EmptyResponse disableAutoRenewal(Number account, String domain) {
        return client.empty(DELETE, account + "/registrar/domains/" + domain + "/auto_renewal", ListOptions.empty(), null);
    }

    /**
     * Gets the whois privacy for the domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The get whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#getWhoisPrivacy">https://developer.dnsimple.com/v2/registrar/whois-privacy/#getWhoisPrivacy</a>
     */
    public SimpleResponse<WhoisPrivacy> getWhoisPrivacy(Number account, String domain) {
        return client.simple(GET, account + "/registrar/domains/" + domain + "/whois_privacy", ListOptions.empty(), null, WhoisPrivacy.class);
    }

    /**
     * Enable whois privacy for the domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The enable whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#enableWhoisPrivacy">https://developer.dnsimple.com/v2/registrar/whois-privacy/#enableWhoisPrivacy</a>
     */
    public SimpleResponse<WhoisPrivacy> enableWhoisPrivacy(Number account, String domain) {
        return client.simple(PUT, account + "/registrar/domains/" + domain + "/whois_privacy", ListOptions.empty(), null, WhoisPrivacy.class);
    }

    /**
     * Disable whois privacy for the domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The disable whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#disableWhoisPrivacy">https://developer.dnsimple.com/v2/registrar/whois-privacy/#disableWhoisPrivacy</a>
     */
    public SimpleResponse<WhoisPrivacy> disableWhoisPrivacy(Number account, String domain) {
        return client.simple(DELETE, account + "/registrar/domains/" + domain + "/whois_privacy", ListOptions.empty(), null, WhoisPrivacy.class);
    }

    /**
     * Renew whois privacy for the domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The disable whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#renewWhoisPrivacy">https://developer.dnsimple.com/v2/registrar/whois-privacy/#renewWhoisPrivacy</a>
     */
    public SimpleResponse<WhoisPrivacyRenewal> renewWhoisPrivacy(Number account, String domain) {
        return client.simple(POST, account + "/registrar/domains/" + domain + "/whois_privacy/renewals", ListOptions.empty(), null, WhoisPrivacyRenewal.class);
    }

    /**
     * Lists name servers the domain is delegating to.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The get domain delegation response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#getDomainDelegation">https://developer.dnsimple.com/v2/registrar/delegation/#getDomainDelegation</a>
     */
    public ListResponse<String> getDomainDelegation(Number account, String domain) {
        return client.list(GET, account + "/registrar/domains/" + domain + "/delegation", ListOptions.empty(), null, String.class);
    }

    /**
     * Change name servers the domain is delegating to.
     *
     * @param account         The account ID
     * @param domain          The domain ID or name
     * @param nameServerNames The name server names to change the delegation to
     * @return The change domain delegation response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#changeDomainDelegation">https://developer.dnsimple.com/v2/registrar/delegation/#changeDomainDelegation</a>
     */
    public ListResponse<String> changeDomainDelegation(Number account, String domain, List<String> nameServerNames) {
        return client.list(PUT, account + "/registrar/domains/" + domain + "/delegation", ListOptions.empty(), nameServerNames, String.class);
    }

    /**
     * Change the domain delegation to the specified vanity name servers.
     *
     * @param account         The account ID
     * @param domain          The domain ID or name
     * @param nameServerNames The vanity name server names
     * @return The change domain delegation to vanity response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#changeDomainDelegationToVanity">https://developer.dnsimple.com/v2/registrar/delegation/#changeDomainDelegationToVanity</a>
     */
    public ListResponse<VanityNameServer> changeDomainDelegationToVanity(Number account, String domain, List<String> nameServerNames) {
        return client.list(PUT, account + "/registrar/domains/" + domain + "/delegation/vanity", ListOptions.empty(), nameServerNames, VanityNameServer.class);
    }

    /**
     * Change the domain delegation back to the standard DNSimple name servers.
     *
     * @param account The account ID
     * @param domain  The domain ID or name
     * @return The change domain delegation from vanity response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#changeDomainDelegationFromVanity">https://developer.dnsimple.com/v2/registrar/delegation/#changeDomainDelegationFromVanity</a>
     */
    public EmptyResponse changeDomainDelegationFromVanity(Number account, String domain) {
        return client.empty(DELETE, account + "/registrar/domains/" + domain + "/delegation/vanity", ListOptions.empty(), null);
    }

    /**
     * List registrant changes in the account.
     *
     * @param account The account ID
     * @param options List options
     * @return Registrant changes
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#listRegistrantChanges">https://developer.dnsimple.com/v2/registrar/#listRegistrantChanges</a>
     */
    public ListResponse<RegistrantChange> listRegistrantChanges(Number account, ListOptions options) {
        return client.list(GET, account + "/registrar/registrant_changes", options, null, RegistrantChange.class);
    }

    /**
     * Start a registrant change.
     *
     * @param account The account ID
     * @param input The input parameters
     * @return The registrant change response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#createRegistrantChange">https://developer.dnsimple.com/v2/registrar/#createRegistrantChange</a>
     */
    public SimpleResponse<RegistrantChange> createRegistrantChange(Number account, CreateRegistrantChangeInput input) {
        return client.simple(POST, account + "/registrar/registrant_changes", ListOptions.empty(), input, RegistrantChange.class);
    }

    /**
     * Retrieves the requirements of a registrant change.
     *
     * @param account The account ID
     * @param input The domain and contact to check
     * @return The registrant change check response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#checkRegistrantChange">https://developer.dnsimple.com/v2/registrar/#checkRegistrantChange</a>
     */
    public SimpleResponse<RegistrantChangeCheck> checkRegistrantChange(Number account, CheckRegistrantChangeInput input) {
        return client.simple(POST, account + "/registrar/registrant_changes/check", ListOptions.empty(), input, RegistrantChangeCheck.class);
    }

    /**
     * Retrieves the details of an existing registrant change.
     *
     * @param account The account ID
     * @param registrantChange The registrant change
     * @return The registrant change response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getRegistrantChange">https://developer.dnsimple.com/v2/registrar/#getRegistrantChange</a>
     */
    public SimpleResponse<RegistrantChange> getRegistrantChange(Number account, Number registrantChange) {
        return client.simple(GET, account + "/registrar/registrant_changes/" + registrantChange, ListOptions.empty(), null, RegistrantChange.class);
    }

    /**
     * Cancel an ongoing registrant change from the account.
     *
     * @param account The account ID
     * @param registrantChange The registrant change
     * @return The registrant change response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#deleteRegistrantChange">https://developer.dnsimple.com/v2/registrar/#deleteRegistrantChange</a>
     */
    public EmptyResponse deleteRegistrantChange(Number account, Number registrantChange) {
        return client.empty(DELETE, account + "/registrar/registrant_changes/" + registrantChange, ListOptions.empty(), null);
    }

    /**
     * Gets the transfer lock status for a domain.
     *
     * @param account The account ID
     * @param domain The domain name or ID
     * @return The transfer lock status
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainTransferLock">https://developer.dnsimple.com/v2/registrar/#getDomainTransferLock</a>
     */
    public SimpleResponse<DomainTransferLock> getDomainTransferLock(Number account, String domain) {
        return client.simple(GET, account + "/registrar/domains/" + domain + "/transfer_lock", ListOptions.empty(), null, DomainTransferLock.class);
    }

    /**
     * Locks the domain to prevent unauthorized transfers.
     *
     * @param account The account ID
     * @param domain The domain name or ID
     * @return The transfer lock status
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#enableDomainTransferLock">https://developer.dnsimple.com/v2/registrar/#enableDomainTransferLock</a>
     */
    public SimpleResponse<DomainTransferLock> enableDomainTransferLock(Number account, String domain) {
        return client.simple(POST, account + "/registrar/domains/" + domain + "/transfer_lock", ListOptions.empty(), null, DomainTransferLock.class);
    }

    /**
     * Unlocks the domain to allow domain transfers.
     *
     * @param account The account ID
     * @param domain The domain name or ID
     * @return The transfer lock status
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#disableDomainTransferLock">https://developer.dnsimple.com/v2/registrar/#disableDomainTransferLock</a>
     */
    public SimpleResponse<DomainTransferLock> disableDomainTransferLock(Number account, String domain) {
        return client.simple(DELETE, account + "/registrar/domains/" + domain + "/transfer_lock", ListOptions.empty(), null, DomainTransferLock.class);
    }
}

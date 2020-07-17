package com.dnsimple.endpoints;

import com.dnsimple.data.*;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.RegistrationOptions;
import com.dnsimple.request.RenewOptions;
import com.dnsimple.request.TransferOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.List;

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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#check">https://developer.dnsimple.com/v2/registrar/#check</a>
     */
    public SimpleResponse<DomainCheck> checkDomain(Number account, String domainName) {
        return client.simple(GET, account + "/registrar/domains/" + domainName + "/check", ListOptions.empty(), null, DomainCheck.class);
    }

    /**
     * Registers a domain.
     *
     * @param account    The account ID
     * @param domainName The domain to register
     * @param options    The options for the domain registration
     * @return The register domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#register">https://developer.dnsimple.com/v2/registrar/#register</a>
     */
    public SimpleResponse<DomainRegistration> registerDomain(Number account, String domainName, RegistrationOptions options) {
        return client.simple(POST, account + "/registrar/domains/" + domainName + "/registrations", ListOptions.empty(), options, DomainRegistration.class);
    }

    /**
     * Renews a domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the renewal
     * @return The renew domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#renew">https://developer.dnsimple.com/v2/registrar/#renew</a>
     */
    public SimpleResponse<DomainRenewal> renewDomain(Number account, String domain, RenewOptions options) {
        return client.simple(POST, account + "/registrar/domains/" + domain + "/renewals", ListOptions.empty(), options, DomainRenewal.class);
    }

    /**
     * Starts the transfer of a domain to DNSimple.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the transfer
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#transfer">https://developer.dnsimple.com/v2/registrar/#transfer</a>
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
     * Requests the transfer of a domain out of DNSimple.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The transfer domain out response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#transfer-out">https://developer.dnsimple.com/v2/registrar/#transfer-out</a>
     */
    public EmptyResponse transferDomainOut(Number account, String domain) {
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#get">https://developer.dnsimple.com/v2/registrar/whois-privacy/#get</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#enable">https://developer.dnsimple.com/v2/registrar/whois-privacy/#enable</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#disable">https://developer.dnsimple.com/v2/registrar/whois-privacy/#disable</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#renew">https://developer.dnsimple.com/v2/registrar/whois-privacy/#renew</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#list">https://developer.dnsimple.com/v2/registrar/delegation/#list</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#update">https://developer.dnsimple.com/v2/registrar/delegation/#update</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#delegateToVanity">https://developer.dnsimple.com/v2/registrar/delegation/#delegateToVanity</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#delegateFromVanity">https://developer.dnsimple.com/v2/registrar/delegation/#delegateFromVanity</a>
     */
    public EmptyResponse changeDomainDelegationFromVanity(Number account, String domain) {
        return client.empty(DELETE, account + "/registrar/domains/" + domain + "/delegation/vanity", ListOptions.empty(), null);
    }
}

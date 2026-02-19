package com.dnsimple.endpoints;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.data.Domain;
import com.dnsimple.data.DomainPush;
import com.dnsimple.data.DomainResearchStatus;
import com.dnsimple.data.Dnssec;
import com.dnsimple.data.EmailForward;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.DSRecordOptions;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.HashMap;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static java.util.Collections.singletonMap;

/**
 * Provides access to the DNSimple Domains API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains">https://developer.dnsimple.com/v2/domains</a>
 */
public class Domains {
    private final HttpEndpointClient client;

    public Domains(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the domains in the account.
     *
     * @param account The account ID
     * @return The list domains response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#listDomains">https://developer.dnsimple.com/v2/domains/#listDomains</a>
     */
    public PaginatedResponse<Domain> listDomains(Number account) {
        return client.page(GET, account + "/domains", ListOptions.empty(), null, Domain.class);
    }

    /**
     * Lists the domains in the account.
     *
     * @param account The account ID
     * @param options The options for the list request
     * @return The list domains response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#listDomains">https://developer.dnsimple.com/v2/domains/#listDomains</a>
     */
    public PaginatedResponse<Domain> listDomains(Number account, ListOptions options) {
        return client.page(GET, account + "/domains", options, null, Domain.class);
    }

    /**
     * Create a domain in an account.
     *
     * @param account The account ID
     * @param name    The name of the domain
     * @return The create domain response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#createDomain">https://developer.dnsimple.com/v2/domains/#createDomain</a>
     */
    public SimpleResponse<Domain> createDomain(Number account, String name) {
        return client.simple(POST, account + "/domains", ListOptions.empty(), singletonMap("name", name), Domain.class);
    }

    /**
     * Get a specific domain associated to an account using the domain's name or ID.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The get domain response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#getDomain">https://developer.dnsimple.com/v2/domains/#getDomain</a>
     */
    public SimpleResponse<Domain> getDomain(Number account, String domain) {
        return client.simple(GET, account + "/domains/" + domain, ListOptions.empty(), null, Domain.class);
    }

    /**
     * Delete a domain from an account.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The delete domain response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#deleteDomain">https://developer.dnsimple.com/v2/domains/#deleteDomain</a>
     */
    public EmptyResponse deleteDomain(Number account, String domain) {
        return client.empty(DELETE, account + "/domains/" + domain, ListOptions.empty(), null);
    }


    /**
     * Enables DNSSEC on the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The DNSSEC enable response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#enableDomainDnssec">https://developer.dnsimple.com/v2/domains/dnssec/#enableDomainDnssec</a>
     */
    public SimpleResponse<Dnssec> enableDnssec(Number account, String domain) {
        return client.simple(POST, account + "/domains/" + domain + "/dnssec", ListOptions.empty(), null, Dnssec.class);
    }

    /**
     * Disables DNSSEC on the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The DNSSEC disable response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#disableDomainDnsec">https://developer.dnsimple.com/v2/domains/dnssec/#disableDomainDnsec</a>
     */
    public EmptyResponse disableDnssec(Number account, String domain) {
        return client.empty(DELETE, account + "/domains/" + domain + "/dnssec", ListOptions.empty(), null);
    }

    /**
     * Get DNSSEC status of the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The get DNSSEC response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#getDomainDnssec">https://developer.dnsimple.com/v2/domains/dnssec/#getDomainDnssec</a>
     */
    public SimpleResponse<Dnssec> getDnssec(Number account, String domain) {
        return client.simple(GET, account + "/domains/" + domain + "/dnssec", ListOptions.empty(), null, Dnssec.class);
    }

    /**
     * Lists the delegation signer records in the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The list delegation signer records response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#listDomainDelegationSignerRecords">https://developer.dnsimple.com/v2/domains/dnssec/#listDomainDelegationSignerRecords</a>
     */
    public PaginatedResponse<DelegationSignerRecord> listDelegationSignerRecords(Number account, String domain) {
        return client.page(GET, account + "/domains/" + domain + "/ds_records", ListOptions.empty(), null, DelegationSignerRecord.class);
    }

    /**
     * Lists the delegation signer records in the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @param options The options for the list request
     * @return The list delegation signer records response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#listDomainDelegationSignerRecords">https://developer.dnsimple.com/v2/domains/dnssec/#listDomainDelegationSignerRecords</a>
     */
    public PaginatedResponse<DelegationSignerRecord> listDelegationSignerRecords(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/ds_records", options, null, DelegationSignerRecord.class);
    }

    /**
     * Create a delegation signer record for a domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options to create the DS record
     * @return The create delegation signer record response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#createDomainDelegationSignerRecord">https://developer.dnsimple.com/v2/domains/dnssec/#createDomainDelegationSignerRecord</a>
     */
    public SimpleResponse<DelegationSignerRecord> createDelegationSignerRecord(Number account, String domain, DSRecordOptions options) {
        return client.simple(POST, account + "/domains/" + domain + "/ds_records", ListOptions.empty(), options, DelegationSignerRecord.class);
    }

    /**
     * Get a delegation signer record for a domain using the delegation signer records's ID.
     *
     * @param account    The account ID
     * @param domain     The domain name or ID
     * @param dsRecordId The delegation signer record ID
     * @return The get delegation signer record response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#getDomainDelegationSignerRecord">https://developer.dnsimple.com/v2/domains/dnssec/#getDomainDelegationSignerRecord</a>
     */
    public SimpleResponse<DelegationSignerRecord> getDelegationSignerRecord(Number account, String domain, Number dsRecordId) {
        return client.simple(GET, account + "/domains/" + domain + "/ds_records/" + dsRecordId, ListOptions.empty(), null, DelegationSignerRecord.class);
    }

    /**
     * Delete a delegation signer record from a domain.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param account    The account ID
     * @param domain     The domain ID or name or name
     * @param dsRecordId The delegation signer record ID
     * @return The delete delegation signer record response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#deleteDomainDelegationSignerRecord">https://developer.dnsimple.com/v2/domains/dnssec/#deleteDomainDelegationSignerRecord</a>
     */
    public EmptyResponse deleteDelegationSignerRecord(Number account, String domain, Number dsRecordId) {
        return client.empty(DELETE, account + "/domains/" + domain + "/ds_records/" + dsRecordId, ListOptions.empty(), null);
    }

    /**
     * List email forwards under a given domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The list email forwards response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#listEmailForwards">https://developer.dnsimple.com/v2/domains/email-forwards/#listEmailForwards</a>
     */
    public PaginatedResponse<EmailForward> listEmailForwards(Number account, String domain) {
        return client.page(GET, account + "/domains/" + domain + "/email_forwards", ListOptions.empty(), null, EmailForward.class);
    }

    /**
     * List email forwards under a given domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @param options The options for the list request
     * @return The list email forwards response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#listEmailForwards">https://developer.dnsimple.com/v2/domains/email-forwards/#listEmailForwards</a>
     */
    public PaginatedResponse<EmailForward> listEmailForwards(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/email_forwards", options, null, EmailForward.class);
    }

    /**
     * Create an email forward for a domain.
     *
     * @param account          The account ID
     * @param domain           The domain name or ID
     * @param aliasName        The email address the emails are send to
     * @param destinationEmail The email address the email address the emails are forwarded to.
     * @return The create email forward response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#createEmailForward">https://developer.dnsimple.com/v2/domains/email-forwards/#createEmailForward</a>
     */
    public SimpleResponse<EmailForward> createEmailForward(Number account, String domain, String aliasName, String destinationEmail) {
        Map<String, String> options = new HashMap<>();
        options.put("alias_name", aliasName);
        options.put("destination_email", destinationEmail);
        return client.simple(POST, account + "/domains/" + domain + "/email_forwards", ListOptions.empty(), options, EmailForward.class);
    }

    /**
     * Get a specific email forward associated to a domain using the email forward's ID.
     *
     * @param account      The account ID
     * @param domain       The domain name or ID
     * @param emailForward The email forward ID
     * @return The get email forward response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#getEmailForward">https://developer.dnsimple.com/v2/domains/email-forwards/#getEmailForward</a>
     */
    public SimpleResponse<EmailForward> getEmailForward(Number account, String domain, Number emailForward) {
        return client.simple(GET, account + "/domains/" + domain + "/email_forwards/" + emailForward, ListOptions.empty(), null, EmailForward.class);
    }

    /**
     * Delete an email forward from a domain.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param account      The account ID
     * @param domain       The domain ID or name or name
     * @param emailForward The email forward ID
     * @return The delete email forward response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#deleteEmailForward">https://developer.dnsimple.com/v2/domains/email-forwards/#deleteEmailForward</a>
     */
    public EmptyResponse deleteEmailForward(Number account, String domain, Number emailForward) {
        return client.empty(DELETE, account + "/domains/" + domain + "/email_forwards/" + emailForward, ListOptions.empty(), null);
    }

    /**
     * Initiate a push.
     *
     * @param account         The account ID
     * @param domain          The domain name or ID
     * @param newAccountEmail The email address of the target DNSimple account
     * @return The initiate push response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#initiateDomainPush">https://developer.dnsimple.com/v2/domains/pushes/#initiateDomainPush</a>
     */
    public SimpleResponse<DomainPush> initiatePush(Number account, String domain, String newAccountEmail) {
        return client.simple(POST, account + "/domains/" + domain + "/pushes", ListOptions.empty(), singletonMap("new_account_email", newAccountEmail), DomainPush.class);
    }

    /**
     * List pushes under a given domain.
     *
     * @param account The account ID
     * @return The list pushes response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#listPushes">https://developer.dnsimple.com/v2/domains/pushes/#listPushes</a>
     */
    public PaginatedResponse<DomainPush> listPushes(Number account) {
        return client.page(GET, account + "/pushes", ListOptions.empty(), null, DomainPush.class);
    }

    /**
     * List pushes under a given domain.
     *
     * @param account The account ID
     * @param options The options for the list request
     * @return The list pushes response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#listPushes">https://developer.dnsimple.com/v2/domains/pushes/#listPushes</a>
     */
    public PaginatedResponse<DomainPush> listPushes(Number account, ListOptions options) {
        return client.page(GET, account + "/pushes", options, null, DomainPush.class);
    }

    /**
     * Accept a push.
     *
     * @param account   The account ID
     * @param push      The push ID
     * @param contactId A contact that belongs to the target DNSimple account
     * @return The accept push response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#acceptPush">https://developer.dnsimple.com/v2/domains/pushes/#acceptPush</a>
     */
    public EmptyResponse acceptPush(Number account, Number push, Number contactId) {
        return client.empty(POST, account + "/pushes/" + push, ListOptions.empty(), singletonMap("contact_id", contactId.longValue()));
    }

    /**
     * Reject a push.
     *
     * @param account The account ID
     * @param push    The push ID
     * @return The accept push response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#rejectPush">https://developer.dnsimple.com/v2/domains/pushes/#rejectPush</a>
     */
    public EmptyResponse rejectPush(Number account, Number push) {
        return client.empty(DELETE, account + "/pushes/" + push, ListOptions.empty(), null);
    }

    /**
     * Research a domain name for availability and registration status information.
     * 
     * This endpoint provides information about a domain's availability status, including whether it's
     * available for registration, already registered, or has other restrictions.
     *
     * @param account     The account ID
     * @param domainName  The domain name to research
     * @return The domain research status response
     * @see <a href="https://developer.dnsimple.com/v2/domains/research/#getDomainsResearchStatus">https://developer.dnsimple.com/v2/domains/research/#getDomainsResearchStatus</a>
     */
    public SimpleResponse<DomainResearchStatus> domainResearchStatus(Number account, String domainName) {
        return client.simple(
                GET,
                account + "/domains/research/status",
                ListOptions.empty().setOtherOption("domain", domainName),
                null,
                DomainResearchStatus.class
        );
    }
}

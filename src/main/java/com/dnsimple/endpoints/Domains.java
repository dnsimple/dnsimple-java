package com.dnsimple.endpoints;

import com.dnsimple.data.*;
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/#list">https://developer.dnsimple.com/v2/domains/#list</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/#list">https://developer.dnsimple.com/v2/domains/#list</a>
     */
    public PaginatedResponse<Domain> listDomains(Number account, ListOptions options) {
        return client.page(GET, account + "/domains", options, null, Domain.class);
    }

    /**
     * Get a specific domain associated to an account using the domain's name or ID.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The get domain response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#get">https://developer.dnsimple.com/v2/domains/#get</a>
     */
    public SimpleResponse<Domain> getDomain(Number account, String domain) {
        return client.simple(GET, account + "/domains/" + domain, ListOptions.empty(), null, Domain.class);
    }

    /**
     * Create a domain in an account.
     *
     * @param account The account ID
     * @param name    The name of the domain
     * @return The create domain response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#create">https://developer.dnsimple.com/v2/domains/#create</a>
     */
    public SimpleResponse<Domain> createDomain(Number account, String name) {
        return client.simple(POST, account + "/domains", ListOptions.empty(), singletonMap("name", name), Domain.class);
    }

    /**
     * Delete a domain from an account.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The delete domain response
     * @see <a href="https://developer.dnsimple.com/v2/domains/#delete">https://developer.dnsimple.com/v2/domains/#delete</a>
     */
    public EmptyResponse deleteDomain(Number account, String domain) {
        return client.empty(DELETE, account + "/domains/" + domain, ListOptions.empty(), null);
    }

    /**
     * Lists the collaborators in the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name
     * @return The list collaborators response
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#list">https://developer.dnsimple.com/v2/domains/collaborators/#list</a>
     */
    public PaginatedResponse<Collaborator> listCollaborators(Number account, String domain) {
        return client.page(GET, account + "/domains/" + domain + "/collaborators", ListOptions.empty(), null, Collaborator.class);
    }

    /**
     * Lists the collaborators in the account.
     *
     * @param account The account ID
     * @param domain  The domain ID or name
     * @param options The options for the list request
     * @return The list collaborators response
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#list">https://developer.dnsimple.com/v2/domains/collaborators/#list</a>
     */
    public PaginatedResponse<Collaborator> listCollaborators(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/collaborators", options, null, Collaborator.class);
    }

    /**
     * Add a collaborator to a domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name
     * @param email   The email of the collaborator
     * @return The add collaborator response
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#add">https://developer.dnsimple.com/v2/domains/collaborators/#create</a>
     */
    public SimpleResponse<Collaborator> addCollaborator(Number account, String domain, String email) {
        return client.simple(POST, account + "/domains/" + domain + "/collaborators", ListOptions.empty(), singletonMap("email", email), Collaborator.class);
    }

    /**
     * Remove a collaborator from a domain.
     *
     * @param account        The account ID
     * @param domain         The domain ID or name
     * @param collaboratorId The collaborator ID
     * @return The remove collaborator response
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#remove">https://developer.dnsimple.com/v2/domains/collaborators/#remove</a>
     */
    public EmptyResponse removeCollaborator(Number account, String domain, String collaboratorId) {
        return client.empty(DELETE, account + "/domains/" + domain + "/collaborators/" + collaboratorId, ListOptions.empty(), null);
    }

    /**
     * Enables DNSSEC on the domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The DNSSEC enable response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#enable">https://developer.dnsimple.com/v2/domains/dnssec/#enable</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#disable">https://developer.dnsimple.com/v2/domains/dnssec/#disable</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#get">https://developer.dnsimple.com/v2/domains/dnssec/#get</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list</a>
     */
    public PaginatedResponse<DelegationSignerRecord> listDelegationSignerRecords(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/ds_records", options, null, DelegationSignerRecord.class);
    }

    /**
     * Get a delegation signer record for a domain using the delegation signer records's ID.
     *
     * @param account    The account ID
     * @param domain     The domain name or ID
     * @param dsRecordId The delegation signer record ID
     * @return The get delegation signer record response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-get">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-get</a>
     */
    public SimpleResponse<DelegationSignerRecord> getDelegationSignerRecord(Number account, String domain, Number dsRecordId) {
        return client.simple(GET, account + "/domains/" + domain + "/ds_records/" + dsRecordId, ListOptions.empty(), null, DelegationSignerRecord.class);
    }

    /**
     * Create a delegation signer record for a domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options to create the DS record
     * @return The create delegation signer record response
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-create">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-create</a>
     */
    public SimpleResponse<DelegationSignerRecord> createDelegationSignerRecord(Number account, String domain, DSRecordOptions options) {
        return client.simple(POST, account + "/domains/" + domain + "/ds_records", ListOptions.empty(), options, DelegationSignerRecord.class);
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-delete">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-delete</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#list">https://developer.dnsimple.com/v2/domains/email-forwards/#list</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#list">https://developer.dnsimple.com/v2/domains/email-forwards/#list</a>
     */
    public PaginatedResponse<EmailForward> listEmailForwards(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/email_forwards", options, null, EmailForward.class);
    }

    /**
     * Get a specific email forward associated to a domain using the email forward's ID.
     *
     * @param account      The account ID
     * @param domain       The domain name or ID
     * @param emailForward The email forward ID
     * @return The get email forward response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#get">https://developer.dnsimple.com/v2/domains/email-forwards/#get</a>
     */
    public SimpleResponse<EmailForward> getEmailForward(Number account, String domain, Number emailForward) {
        return client.simple(GET, account + "/domains/" + domain + "/email_forwards/" + emailForward, ListOptions.empty(), null, EmailForward.class);
    }

    /**
     * Create an email forward for a domain.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param from    The email address the emails are send to
     * @param to      The email address the email address the emails are forwarded to.
     * @return The create email forward response
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#create">https://developer.dnsimple.com/v2/domains/email-forwards/#create</a>
     */
    public SimpleResponse<EmailForward> createEmailForward(Number account, String domain, String from, String to) {
        Map<String, String> options = new HashMap<>();
        options.put("from", from);
        options.put("to", to);
        return client.simple(POST, account + "/domains/" + domain + "/email_forwards", ListOptions.empty(), options, EmailForward.class);
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#delete">https://developer.dnsimple.com/v2/domains/email-forwards/#delete</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#initiate">https://developer.dnsimple.com/v2/domains/pushes/#initiate</a>
     */
    public SimpleResponse<DomainPush> initiatePush(Number account, String domain, String newAccountEmail) {
        return client.simple(POST, account + "/domains/" + domain + "/pushes", ListOptions.empty(), singletonMap("new_account_email", newAccountEmail), DomainPush.class);
    }

    /**
     * List pushes under a given domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @return The list pushes response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#list">https://developer.dnsimple.com/v2/domains/pushes/#list</a>
     */
    public PaginatedResponse<DomainPush> listPushes(Number account, String domain) {
        return client.page(GET, account + "/domains/" + domain + "/pushes", ListOptions.empty(), null, DomainPush.class);
    }

    /**
     * List pushes under a given domain.
     *
     * @param account The account ID
     * @param domain  The domain ID or name or name
     * @param options The options for the list request
     * @return The list pushes response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#list">https://developer.dnsimple.com/v2/domains/pushes/#list</a>
     */
    public PaginatedResponse<DomainPush> listPushes(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/pushes", options, null, DomainPush.class);
    }

    /**
     * Accept a push.
     *
     * @param account   The account ID
     * @param push      The push ID
     * @param contactId A contact that belongs to the target DNSimple account
     * @return The accept push response
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#accept">https://developer.dnsimple.com/v2/domains/pushes/#accept</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#reject">https://developer.dnsimple.com/v2/domains/pushes/#reject</a>
     */
    public EmptyResponse rejectPush(Number account, Number push) {
        return client.empty(DELETE, account + "/pushes/" + push, ListOptions.empty(), null);
    }
}

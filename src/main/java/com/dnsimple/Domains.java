package com.dnsimple;

import com.dnsimple.data.*;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

/**
 * Provides access to the DNSimple Domains API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains">https://developer.dnsimple.com/v2/domains</a>
 */
public interface Domains {
    // Domains

    /**
     * Lists the domains in the account.
     *
     * @param accountId The account ID
     * @return The list domains response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/#list">https://developer.dnsimple.com/v2/domains/#list</a>
     */
    PaginatedResponse<Domain> listDomains(String accountId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the domains in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the domains API
     * @return The list domains response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/#list">https://developer.dnsimple.com/v2/domains/#list</a>
     */
    PaginatedResponse<Domain> listDomains(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get a specific domain associated to an account using the domain's name or ID.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The get domain response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/#get">https://developer.dnsimple.com/v2/domains/#get</a>
     */
    SimpleResponse<Domain> getDomain(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Create a domain in an account.
     *
     * @param accountId  The account ID
     * @param attributes A Map of attributes for constructing the domain
     * @return The create domain response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/#create">https://developer.dnsimple.com/v2/domains/#create</a>
     */
    SimpleResponse<Domain> createDomain(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Delete a domain from an account.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The delete domain response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/#delete">https://developer.dnsimple.com/v2/domains/#delete</a>
     */
    EmptyResponse deleteDomain(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Resets the domain token.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The reset token domain response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/#reset-token">https://developer.dnsimple.com/v2/domains/#reset-token</a>
     */
    SimpleResponse<Domain> resetDomainToken(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;
    // Collaborators

    /**
     * Lists the collaborators in the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID
     * @return The list collaborators response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#list">https://developer.dnsimple.com/v2/domains/collaborators/#list</a>
     */
    PaginatedResponse<Collaborator> listCollaborators(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the collaborators in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID
     * @param options   A Map of options to pass to the collaborators API
     * @return The list collaborators response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#list">https://developer.dnsimple.com/v2/domains/collaborators/#list</a>
     */
    PaginatedResponse<Collaborator> listCollaborators(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Add a collaborator to a domain.
     *
     * @param accountId  The account ID
     * @param domainId   The domain ID
     * @param attributes A Map of attributes for constructing the domain
     * @return The add collaborator response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#add">https://developer.dnsimple.com/v2/domains/collaborators/#create</a>
     */
    SimpleResponse<Collaborator> addCollaborator(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Remove a collaborator from a domain.
     *
     * @param accountId      The account ID
     * @param domainId       The domain ID
     * @param collaboratorId The collaborator ID
     * @return The remove collaborator response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#remove">https://developer.dnsimple.com/v2/domains/collaborators/#remove</a>
     */
    EmptyResponse removeCollaborator(String accountId, String domainId, String collaboratorId) throws DnsimpleException, IOException, InterruptedException;
    // DNSSEC

    /**
     * Enables DNSSEC on the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The DNSSEC enable response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#enable">https://developer.dnsimple.com/v2/domains/dnssec/#enable</a>
     */
    SimpleResponse<Dnssec> enableDnssec(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Disables DNSSEC on the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The DNSSEC disable response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#disable">https://developer.dnsimple.com/v2/domains/dnssec/#disable</a>
     */
    EmptyResponse disableDnssec(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get DNSSEC status of the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The get DNSSEC response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#get">https://developer.dnsimple.com/v2/domains/dnssec/#get</a>
     */
    SimpleResponse<Dnssec> getDnssec(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;
    // Delegation Signer Records

    /**
     * Lists the delegation signer records in the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The list delegation signer records response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list</a>
     */
    PaginatedResponse<DelegationSignerRecord> listDelegationSignerRecords(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the delegation signer records in the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @param options   A Map of options to send to the API
     * @return The list delegation signer records response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-list</a>
     */
    PaginatedResponse<DelegationSignerRecord> listDelegationSignerRecords(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get a delegation signer record for a domain using the delegation signer records's ID.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param dsRecordId The delegation signer record ID
     * @return The get delegation signer record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-get">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-get</a>
     */
    SimpleResponse<DelegationSignerRecord> getDelegationSignerRecord(String accountId, String domainId, String dsRecordId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Create a delegation signer record for a domain.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param attributes A Map of attributes for constructing the delegation signer record
     * @return The create delegation signer record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-create">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-create</a>
     */
    SimpleResponse<DelegationSignerRecord> createDelegationSignerRecord(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Delete a delegation signer record from a domain.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param accountId  The account ID
     * @param domainId   The domain ID or name
     * @param dsRecordId The delegation signer record ID
     * @return The delete delegation signer record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-delete">https://developer.dnsimple.com/v2/domains/dnssec/#ds-record-delete</a>
     */
    EmptyResponse deleteDelegationSignerRecord(String accountId, String domainId, String dsRecordId) throws DnsimpleException, IOException, InterruptedException;
    // Email Forwards

    /**
     * List email forwards under a given domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The list email forwards response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#list">https://developer.dnsimple.com/v2/domains/email-forwards/#list</a>
     */
    PaginatedResponse<EmailForward> listEmailForwards(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * List email forwards under a given domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @param options   A Map of options to send to the API
     * @return The list email forwards response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#list">https://developer.dnsimple.com/v2/domains/email-forwards/#list</a>
     */
    PaginatedResponse<EmailForward> listEmailForwards(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get a specific email forward associated to a domain using the email forward's ID.
     *
     * @param accountId      The account ID
     * @param domainId       The domain name or ID
     * @param emailForwardId The email forward ID
     * @return The get email forward response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#get">https://developer.dnsimple.com/v2/domains/email-forwards/#get</a>
     */
    SimpleResponse<EmailForward> getEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Create an email forward for a domain.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param attributes A Map of attributes for constructing the email forward
     * @return The create email forward response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#create">https://developer.dnsimple.com/v2/domains/email-forwards/#create</a>
     */
    SimpleResponse<EmailForward> createEmailForward(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Delete an email forward from a domain.
     * <p>
     * WARNING: this cannot be undone.
     *
     * @param accountId      The account ID
     * @param domainId       The domain ID or name
     * @param emailForwardId The email forward ID
     * @return The delete email forward response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/email-forwards/#delete">https://developer.dnsimple.com/v2/domains/email-forwards/#delete</a>
     */
    EmptyResponse deleteEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException, InterruptedException;
    // Pushes

    /**
     * Initiate a push.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param attributes A Map of attributes for constructing the push
     * @return The initiate push response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#initiate">https://developer.dnsimple.com/v2/domains/pushes/#initiate</a>
     */
    SimpleResponse<Push> initiatePush(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * List pushes under a given domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The list pushes response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#list">https://developer.dnsimple.com/v2/domains/pushes/#list</a>
     */
    PaginatedResponse<Push> listPushes(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * List pushes under a given domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @param options   A Map of options to send to the API
     * @return The list pushes response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#list">https://developer.dnsimple.com/v2/domains/pushes/#list</a>
     */
    PaginatedResponse<Push> listPushes(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Accept a push.
     *
     * @param accountId  The account ID
     * @param pushId     The push ID
     * @param attributes A Map of attributes required when accepting the push
     * @return The accept push response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#accept">https://developer.dnsimple.com/v2/domains/pushes/#accept</a>
     */
    EmptyResponse acceptPush(String accountId, String pushId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Reject a push.
     *
     * @param accountId The account ID
     * @param pushId    The push ID
     * @return The accept push response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/pushes/#reject">https://developer.dnsimple.com/v2/domains/pushes/#reject</a>
     */
    EmptyResponse rejectPush(String accountId, String pushId) throws DnsimpleException, IOException, InterruptedException;
}

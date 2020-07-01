package com.dnsimple.endpoints.http;

import com.dnsimple.Domains;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.Map;

/**
 * Provides access to the DNSimple Domains API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains">https://developer.dnsimple.com/v2/domains</a>
 */
public class DomainsEndpoint implements Domains {
    private HttpEndpointClient client;

    public DomainsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }
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
    public ListDomainsResponse listDomains(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listDomains(accountId, null);
    }

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
    public ListDomainsResponse listDomains(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListDomainsResponse) client.get(accountId + "/domains", options, ListDomainsResponse.class);
    }

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
    public GetDomainResponse getDomain(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (GetDomainResponse) client.get(accountId + "/domains/" + domainId, null, GetDomainResponse.class);
    }

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
    public CreateDomainResponse createDomain(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (CreateDomainResponse) client.post(accountId + "/domains", attributes, null, CreateDomainResponse.class);
    }

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
    public DeleteDomainResponse deleteDomain(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (DeleteDomainResponse) client.delete(accountId + "/domains/" + domainId, null, DeleteDomainResponse.class);
    }

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
    public ResetDomainTokenResponse resetDomainToken(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (ResetDomainTokenResponse) client.post(accountId + "/domains/" + domainId, null, null, ResetDomainTokenResponse.class);
    }

    /**
     * Lists the collaborators in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID
     * @return The list collaborators response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/collaborators/#list">https://developer.dnsimple.com/v2/collaborators/#list</a>
     */
    public ListCollaboratorsResponse listCollaborators(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return listCollaborators(accountId, domainId, null);
    }

    /**
     * Lists the collaborators in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID
     * @param options   A Map of options to pass to the collaborators API
     * @return The list collaborators response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/collaborators/#list">https://developer.dnsimple.com/v2/collaborators/#list</a>
     */
    public ListCollaboratorsResponse listCollaborators(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListCollaboratorsResponse) client.get(accountId + "/domains/" + domainId + "/collaborators", options, ListCollaboratorsResponse.class);
    }

    /**
     * Add a collaborator to a domain.
     *
     * @param accountId  The account ID
     * @param domainId   The domain ID
     * @param attributes A Map of attributes for adding the collaborator
     * @return The add collaborator response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#add">https://developer.dnsimple.com/v2/domains/collaborators/#add</a>
     */
    public AddCollaboratorResponse addCollaborator(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (AddCollaboratorResponse) client.post(accountId + "/domains/" + domainId + "/collaborators", attributes, null, AddCollaboratorResponse.class);
    }

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
    public RemoveCollaboratorResponse removeCollaborator(String accountId, String domainId, String collaboratorId) throws DnsimpleException, IOException, InterruptedException {
        return (RemoveCollaboratorResponse) client.delete(accountId + "/domains/" + domainId + "/collaborators/" + collaboratorId, null, RemoveCollaboratorResponse.class);
    }

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
    public EnableDnssecResponse enableDnssec(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (EnableDnssecResponse) client.post(accountId + "/domains/" + domainId + "/dnssec", null, null, EnableDnssecResponse.class);
    }

    /**
     * Disable DNSSEC on the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID or name
     * @return The DNSSEC disable response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/dnssec/#disable">https://developer.dnsimple.com/v2/domains/dnssec/#disable</a>
     */
    public DisableDnssecResponse disableDnssec(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (DisableDnssecResponse) client.delete(accountId + "/domains/" + domainId + "/dnssec", null, DisableDnssecResponse.class);
    }

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
    public GetDnssecResponse getDnssec(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (GetDnssecResponse) client.get(accountId + "/domains/" + domainId + "/dnssec", null, GetDnssecResponse.class);
    }
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
    public ListDelegationSignerRecordsResponse listDelegationSignerRecords(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return listDelegationSignerRecords(accountId, domainId, null);
    }

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
    public ListDelegationSignerRecordsResponse listDelegationSignerRecords(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListDelegationSignerRecordsResponse) client.get(accountId + "/domains/" + domainId + "/ds_records", options, ListDelegationSignerRecordsResponse.class);
    }

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
    public GetDelegationSignerRecordResponse getDelegationSignerRecord(String accountId, String domainId, String dsRecordId) throws DnsimpleException, IOException, InterruptedException {
        return (GetDelegationSignerRecordResponse) client.get(accountId + "/domains/" + domainId + "/ds_records/" + dsRecordId, null, GetDelegationSignerRecordResponse.class);
    }

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
    public CreateDelegationSignerRecordResponse createDelegationSignerRecord(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (CreateDelegationSignerRecordResponse) client.post(accountId + "/domains/" + domainId + "/ds_records", attributes, null, CreateDelegationSignerRecordResponse.class);
    }

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
    public DeleteDelegationSignerRecordResponse deleteDelegationSignerRecord(String accountId, String domainId, String dsRecordId) throws DnsimpleException, IOException, InterruptedException {
        return (DeleteDelegationSignerRecordResponse) client.delete(accountId + "/domains/" + domainId + "/ds_records/" + dsRecordId, null, DeleteDelegationSignerRecordResponse.class);
    }
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
    public ListEmailForwardsResponse listEmailForwards(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return listEmailForwards(accountId, domainId, null);
    }

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
    public ListEmailForwardsResponse listEmailForwards(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListEmailForwardsResponse) client.get(accountId + "/domains/" + domainId + "/email_forwards", options, ListEmailForwardsResponse.class);
    }

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
    public GetEmailForwardResponse getEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException, InterruptedException {
        return (GetEmailForwardResponse) client.get(accountId + "/domains/" + domainId + "/email_forwards/" + emailForwardId, null, GetEmailForwardResponse.class);
    }

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
    public CreateEmailForwardResponse createEmailForward(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (CreateEmailForwardResponse) client.post(accountId + "/domains/" + domainId + "/email_forwards", attributes, null, CreateEmailForwardResponse.class);
    }

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
    public DeleteEmailForwardResponse deleteEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException, InterruptedException {
        return (DeleteEmailForwardResponse) client.delete(accountId + "/domains/" + domainId + "/email_forwards/" + emailForwardId, null, DeleteEmailForwardResponse.class);
    }
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
    public InitiatePushResponse initiatePush(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (InitiatePushResponse) client.post(accountId + "/domains/" + domainId + "/pushes", attributes, null, InitiatePushResponse.class);
    }

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
    public ListPushesResponse listPushes(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return listPushes(accountId, domainId, null);
    }

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
    public ListPushesResponse listPushes(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListPushesResponse) client.get(accountId + "/domains/" + domainId + "/pushes", options, ListPushesResponse.class);
    }

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
    public AcceptPushResponse acceptPush(String accountId, String pushId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (AcceptPushResponse) client.post(accountId + "/pushes/" + pushId, attributes, null, AcceptPushResponse.class);
    }

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
    public RejectPushResponse rejectPush(String accountId, String pushId) throws DnsimpleException, IOException, InterruptedException {
        return (RejectPushResponse) client.delete(accountId + "/pushes/" + pushId, null, RejectPushResponse.class);
    }
}

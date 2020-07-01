package com.dnsimple.endpoints.http;

import com.dnsimple.Registrar;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegistrarEndpoint implements Registrar {
    private HttpEndpointClient client;

    public RegistrarEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public CheckDomainResponse checkDomain(String accountId, String domainName) throws DnsimpleException, IOException, InterruptedException {
        return (CheckDomainResponse) client.get(accountId + "/registrar/domains/" + domainName + "/check", null, CheckDomainResponse.class);
    }

    public RegisterDomainResponse registerDomain(String accountId, String domainName, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (RegisterDomainResponse) client.post(accountId + "/registrar/domains/" + domainName + "/registrations", attributes, null, RegisterDomainResponse.class);
    }

    public RenewDomainResponse renewDomain(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (RenewDomainResponse) client.post(accountId + "/registrar/domains/" + domainId + "/renewals", attributes, null, RenewDomainResponse.class);
    }

    public TransferDomainResponse transferDomain(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (TransferDomainResponse) client.post(accountId + "/registrar/domains/" + domainId + "/transfers", attributes, null, TransferDomainResponse.class);
    }

    public TransferDomainResponse getDomainTransfer(String accountId, String domainId, String domainTransferId) throws DnsimpleException, IOException, InterruptedException {
        return (TransferDomainResponse) client.get(accountId + "/registrar/domains/" + domainId + "/transfers/" + domainTransferId, null, TransferDomainResponse.class);
    }

    public TransferDomainResponse cancelDomainTransfer(String accountId, String domainId, String domainTransferId) throws DnsimpleException, IOException, InterruptedException {
        return (TransferDomainResponse) client.delete(accountId + "/registrar/domains/" + domainId + "/transfers/" + domainTransferId, null, TransferDomainResponse.class);
    }

    public TransferDomainOutResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (TransferDomainOutResponse) client.post(accountId + "/registrar/domains/" + domainId + "/authorize_transfer_out", null, null, TransferDomainOutResponse.class);
    }

    public EnableAutoRenewalResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (EnableAutoRenewalResponse) client.put(accountId + "/registrar/domains/" + domainId + "/auto_renewal", null, null, EnableAutoRenewalResponse.class);
    }

    public DisableAutoRenewalResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (DisableAutoRenewalResponse) client.delete(accountId + "/registrar/domains/" + domainId + "/auto_renewal", null, DisableAutoRenewalResponse.class);
    }

    public GetWhoisPrivacyResponse getWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (GetWhoisPrivacyResponse) client.get(accountId + "/registrar/domains/" + domainId + "/whois_privacy", null, GetWhoisPrivacyResponse.class);
    }

    public EnableWhoisPrivacyResponse enableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (EnableWhoisPrivacyResponse) client.put(accountId + "/registrar/domains/" + domainId + "/whois_privacy", null, null, EnableWhoisPrivacyResponse.class);
    }

    public DisableWhoisPrivacyResponse disableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (DisableWhoisPrivacyResponse) client.delete(accountId + "/registrar/domains/" + domainId + "/whois_privacy", null, DisableWhoisPrivacyResponse.class);
    }

    public RenewWhoisPrivacyResponse renewWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (RenewWhoisPrivacyResponse) client.post(accountId + "/registrar/domains/" + domainId + "/whois_privacy/renewals", null, null, RenewWhoisPrivacyResponse.class);
    }

    public GetDomainDelegationResponse getDomainDelegation(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (GetDomainDelegationResponse) client.get(accountId + "/registrar/domains/" + domainId + "/delegation", null, GetDomainDelegationResponse.class);
    }

    public ChangeDomainDelegationResponse changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException, InterruptedException {
        return (ChangeDomainDelegationResponse) client.put(accountId + "/registrar/domains/" + domainId + "/delegation", nameServerNames, null, ChangeDomainDelegationResponse.class);
    }

    public ChangeDomainDelegationToVanityResponse changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException, InterruptedException {
        return (ChangeDomainDelegationToVanityResponse) client.put(accountId + "/registrar/domains/" + domainId + "/delegation/vanity", nameServerNames, null, ChangeDomainDelegationToVanityResponse.class);
    }

    public ChangeDomainDelegationFromVanityResponse changeDomainDelegationFromVanity(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (ChangeDomainDelegationFromVanityResponse) client.delete(accountId + "/registrar/domains/" + domainId + "/delegation/vanity", null, ChangeDomainDelegationFromVanityResponse.class);
    }
}

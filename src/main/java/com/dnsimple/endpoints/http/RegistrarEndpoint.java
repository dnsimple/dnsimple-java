package com.dnsimple.endpoints.http;

import com.dnsimple.Registrar;
import com.dnsimple.data.*;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.emptyMap;

public class RegistrarEndpoint implements Registrar {
    private final HttpEndpointClient client;

    public RegistrarEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public SimpleResponse<DomainAvailability> checkDomain(String accountId, String domainName) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/registrar/domains/" + domainName + "/check", emptyMap(), null, DomainAvailability.class);
    }

    public SimpleResponse<DomainRegistration> registerDomain(String accountId, String domainName, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(POST, accountId + "/registrar/domains/" + domainName + "/registrations", emptyMap(), attributes, DomainRegistration.class);
    }

    public SimpleResponse<DomainRenewal> renewDomain(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(POST, accountId + "/registrar/domains/" + domainId + "/renewals", emptyMap(), attributes, DomainRenewal.class);
    }

    public SimpleResponse<DomainTransfer> transferDomain(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(POST, accountId + "/registrar/domains/" + domainId + "/transfers", emptyMap(), attributes, DomainTransfer.class);
    }

    public SimpleResponse<DomainTransfer> getDomainTransfer(String accountId, String domainId, String domainTransferId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/registrar/domains/" + domainId + "/transfers/" + domainTransferId, emptyMap(), null, DomainTransfer.class);
    }

    public SimpleResponse<DomainTransfer> cancelDomainTransfer(String accountId, String domainId, String domainTransferId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(DELETE, accountId + "/registrar/domains/" + domainId + "/transfers/" + domainTransferId, emptyMap(), null, DomainTransfer.class);
    }

    public EmptyResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(POST, accountId + "/registrar/domains/" + domainId + "/authorize_transfer_out", emptyMap(), null);
    }

    public EmptyResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(PUT, accountId + "/registrar/domains/" + domainId + "/auto_renewal", emptyMap(), null);
    }

    public EmptyResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(DELETE, accountId + "/registrar/domains/" + domainId + "/auto_renewal", emptyMap(), null);
    }

    public SimpleResponse<WhoisPrivacy> getWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/registrar/domains/" + domainId + "/whois_privacy", emptyMap(), null, WhoisPrivacy.class);
    }

    public SimpleResponse<WhoisPrivacy> enableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(PUT, accountId + "/registrar/domains/" + domainId + "/whois_privacy", emptyMap(), null, WhoisPrivacy.class);
    }

    public SimpleResponse<WhoisPrivacy> disableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(DELETE, accountId + "/registrar/domains/" + domainId + "/whois_privacy", emptyMap(), null, WhoisPrivacy.class);
    }

    public SimpleResponse<WhoisPrivacyRenewal> renewWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(POST, accountId + "/registrar/domains/" + domainId + "/whois_privacy/renewals", emptyMap(), null, WhoisPrivacyRenewal.class);
    }

    public ListResponse<String> getDomainDelegation(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.list(GET, accountId + "/registrar/domains/" + domainId + "/delegation", emptyMap(), null, String.class);
    }

    public ListResponse<String> changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException, InterruptedException {
        return client.list(PUT, accountId + "/registrar/domains/" + domainId + "/delegation", emptyMap(), nameServerNames, String.class);
    }

    public ListResponse<NameServer> changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException, InterruptedException {
        return client.list(PUT, accountId + "/registrar/domains/" + domainId + "/delegation/vanity", emptyMap(), nameServerNames, NameServer.class);
    }

    public EmptyResponse changeDomainDelegationFromVanity(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(DELETE, accountId + "/registrar/domains/" + domainId + "/delegation/vanity", emptyMap(), null);
    }
}

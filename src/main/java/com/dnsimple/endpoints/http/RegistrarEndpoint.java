package com.dnsimple.endpoints.http;

import com.dnsimple.Registrar;
import com.dnsimple.data.*;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.dnsimple.endpoints.http.java11.HttpMethod.GET;
import static com.dnsimple.endpoints.http.java11.HttpMethod.POST;
import static java.util.Collections.emptyMap;

public class RegistrarEndpoint implements Registrar {
    private final HttpEndpointClient client;

    public RegistrarEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public SimpleResponse<DomainAvailability> checkDomain(String accountId, String domainName) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/registrar/domains/" + domainName + "/check", null, Collections.emptyMap(), DomainAvailability.class);
    }

    public SimpleResponse<DomainRegistration> registerDomain(String accountId, String domainName, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/registrar/domains/" + domainName + "/registrations", attributes, null, DomainRegistration.class);
    }

    public SimpleResponse<DomainRenewal> renewDomain(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/registrar/domains/" + domainId + "/renewals", attributes, null, DomainRenewal.class);
    }

    public SimpleResponse<DomainTransfer> transferDomain(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/registrar/domains/" + domainId + "/transfers", attributes, null, DomainTransfer.class);
    }

    public SimpleResponse<DomainTransfer> getDomainTransfer(String accountId, String domainId, String domainTransferId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/registrar/domains/" + domainId + "/transfers/" + domainTransferId, null, Collections.emptyMap(), DomainTransfer.class);
    }

    public SimpleResponse<DomainTransfer> cancelDomainTransfer(String accountId, String domainId, String domainTransferId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteSimple(accountId + "/registrar/domains/" + domainId + "/transfers/" + domainTransferId, null, DomainTransfer.class);
    }

    public EmptyResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(POST, accountId + "/registrar/domains/" + domainId + "/authorize_transfer_out", null, null);
    }

    public EmptyResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.putEmpty(accountId + "/registrar/domains/" + domainId + "/auto_renewal", null, null);
    }

    public EmptyResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/registrar/domains/" + domainId + "/auto_renewal", null);
    }

    public SimpleResponse<WhoisPrivacy> getWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/registrar/domains/" + domainId + "/whois_privacy", null, Collections.emptyMap(), WhoisPrivacy.class);
    }

    public SimpleResponse<WhoisPrivacy> enableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.putSimple(accountId + "/registrar/domains/" + domainId + "/whois_privacy", null, null, WhoisPrivacy.class);
    }

    public SimpleResponse<WhoisPrivacy> disableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteSimple(accountId + "/registrar/domains/" + domainId + "/whois_privacy", null, WhoisPrivacy.class);
    }

    public SimpleResponse<WhoisPrivacyRenewal> renewWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/registrar/domains/" + domainId + "/whois_privacy/renewals", null, null, WhoisPrivacyRenewal.class);
    }

    public ListResponse<String> getDomainDelegation(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.list(GET, accountId + "/registrar/domains/" + domainId + "/delegation", null, emptyMap(), String.class);
    }

    public ListResponse<String> changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException, InterruptedException {
        return client.putList(accountId + "/registrar/domains/" + domainId + "/delegation", null, nameServerNames, String.class);
    }

    public ListResponse<NameServer> changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException, InterruptedException {
        return client.putList(accountId + "/registrar/domains/" + domainId + "/delegation/vanity", null, nameServerNames, NameServer.class);
    }

    public EmptyResponse changeDomainDelegationFromVanity(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/registrar/domains/" + domainId + "/delegation/vanity", null);
    }
}

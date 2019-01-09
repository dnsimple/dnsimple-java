package com.dnsimple.endpoints.http;

import com.dnsimple.Registrar;
import com.dnsimple.response.CheckDomainResponse;
import com.dnsimple.response.RegisterDomainResponse;
import com.dnsimple.response.RenewDomainResponse;
import com.dnsimple.response.TransferDomainResponse;
import com.dnsimple.response.TransferDomainOutResponse;
import com.dnsimple.response.EnableAutoRenewalResponse;
import com.dnsimple.response.DisableAutoRenewalResponse;
import com.dnsimple.response.GetWhoisPrivacyResponse;
import com.dnsimple.response.EnableWhoisPrivacyResponse;
import com.dnsimple.response.DisableWhoisPrivacyResponse;
import com.dnsimple.response.RenewWhoisPrivacyResponse;
import com.dnsimple.response.GetDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationToVanityResponse;
import com.dnsimple.response.ChangeDomainDelegationFromVanityResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RegistrarEndpoint implements Registrar {
  private HttpEndpointClient client;

  public RegistrarEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public CheckDomainResponse checkDomain(String accountId, String domainName) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/registrar/domains/" + domainName + "/check");
    return (CheckDomainResponse)client.parseResponse(response, CheckDomainResponse.class);
  }

  public RegisterDomainResponse registerDomain(String accountId, String domainName, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainName + "/registrations", attributes);
    return (RegisterDomainResponse)client.parseResponse(response, RegisterDomainResponse.class);
  }

  public RenewDomainResponse renewDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/renewals", attributes);
    return (RenewDomainResponse)client.parseResponse(response, RenewDomainResponse.class);
  }

  public TransferDomainResponse transferDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/transfers", attributes);
    return (TransferDomainResponse)client.parseResponse(response, TransferDomainResponse.class);
  }

  public TransferDomainOutResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/authorize_transfer_out");
    return (TransferDomainOutResponse)client.parseResponse(response, TransferDomainOutResponse.class);
  }

  public EnableAutoRenewalResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.put(accountId + "/registrar/domains/" + domainId + "/auto_renewal");
    return (EnableAutoRenewalResponse)client.parseResponse(response, EnableAutoRenewalResponse.class);
  }

  public DisableAutoRenewalResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/registrar/domains/" + domainId + "/auto_renewal");
    return (DisableAutoRenewalResponse)client.parseResponse(response, DisableAutoRenewalResponse.class);
  }

  public GetWhoisPrivacyResponse getWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/registrar/domains/" + domainId + "/whois_privacy");
    return (GetWhoisPrivacyResponse)client.parseResponse(response, GetWhoisPrivacyResponse.class);
  }

  public EnableWhoisPrivacyResponse enableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.put(accountId + "/registrar/domains/" + domainId + "/whois_privacy");
    return (EnableWhoisPrivacyResponse)client.parseResponse(response, EnableWhoisPrivacyResponse.class);
  }

  public DisableWhoisPrivacyResponse disableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/registrar/domains/" + domainId + "/whois_privacy");
    return (DisableWhoisPrivacyResponse)client.parseResponse(response, DisableWhoisPrivacyResponse.class);
  }

  public RenewWhoisPrivacyResponse renewWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/registrar/domains/" + domainId + "/whois_privacy/renewals");
    return (RenewWhoisPrivacyResponse)client.parseResponse(response, RenewWhoisPrivacyResponse.class);
  }

  public GetDomainDelegationResponse getDomainDelegation(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/registrar/domains/" + domainId + "/delegation");
    return (GetDomainDelegationResponse)client.parseResponse(response, GetDomainDelegationResponse.class);
  }

  public ChangeDomainDelegationResponse changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException {
    HttpResponse response = client.put(accountId + "/registrar/domains/" + domainId + "/delegation", nameServerNames);
    return (ChangeDomainDelegationResponse)client.parseResponse(response, ChangeDomainDelegationResponse.class);
  }

  public ChangeDomainDelegationToVanityResponse changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException {
    HttpResponse response = client.put(accountId + "/registrar/domains/" + domainId + "/delegation/vanity", nameServerNames);
    return (ChangeDomainDelegationToVanityResponse)client.parseResponse(response, ChangeDomainDelegationToVanityResponse.class);
  }

  public ChangeDomainDelegationFromVanityResponse changeDomainDelegationFromVanity(String accountId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/registrar/domains/" + domainId + "/delegation/vanity");
    return (ChangeDomainDelegationFromVanityResponse)client.parseResponse(response, ChangeDomainDelegationFromVanityResponse.class);
  }
}

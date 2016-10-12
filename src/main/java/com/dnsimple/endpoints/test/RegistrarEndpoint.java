package com.dnsimple.endpoints.test;

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
import com.dnsimple.response.GetDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationToVanityResponse;
import com.dnsimple.response.ChangeDomainDelegationFromVanityResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegistrarEndpoint implements Registrar {

  public CheckDomainResponse checkDomain(String accountId, String domainName) throws DnsimpleException, IOException {
    return new CheckDomainResponse();
  }

  public RegisterDomainResponse registerDomain(String accountId, String domainName, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new RegisterDomainResponse();
  }

  public RenewDomainResponse renewDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new RenewDomainResponse();
  }

  public TransferDomainResponse transferDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new TransferDomainResponse();
  }

  public TransferDomainOutResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException {
    return new TransferDomainOutResponse();
  }

  public EnableAutoRenewalResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException {
    return new EnableAutoRenewalResponse();
  }

  public DisableAutoRenewalResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException {
    return new DisableAutoRenewalResponse();
  }

  public GetWhoisPrivacyResponse getWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    return new GetWhoisPrivacyResponse();
  }

  public EnableWhoisPrivacyResponse enableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    return new EnableWhoisPrivacyResponse();
  }

  public DisableWhoisPrivacyResponse disableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException {
    return new DisableWhoisPrivacyResponse();
  }

  public GetDomainDelegationResponse getDomainDelegation(String accountId, String domainId) throws DnsimpleException, IOException {
    return new GetDomainDelegationResponse();
  }

  public ChangeDomainDelegationResponse changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException {
    return new ChangeDomainDelegationResponse();
  }

  public ChangeDomainDelegationToVanityResponse changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException {
    return new ChangeDomainDelegationToVanityResponse();
  }

  public ChangeDomainDelegationFromVanityResponse changeDomainDelegationFromVanity(String accountId, String domainId) throws DnsimpleException, IOException {
    return new ChangeDomainDelegationFromVanityResponse();
  }

}


package com.dnsimple.endpoints.test;

import com.dnsimple.Domains;
import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.GetDomainResponse;
import com.dnsimple.response.CreateDomainResponse;
import com.dnsimple.response.DeleteDomainResponse;
import com.dnsimple.response.ResetDomainTokenResponse;
import com.dnsimple.response.ListEmailForwardsResponse;
import com.dnsimple.response.GetEmailForwardResponse;
import com.dnsimple.response.CreateEmailForwardResponse;
import com.dnsimple.response.DeleteEmailForwardResponse;
import com.dnsimple.response.InitiatePushResponse;
import com.dnsimple.response.ListPushesResponse;
import com.dnsimple.response.AcceptPushResponse;
import com.dnsimple.response.RejectPushResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DomainsEndpoint implements Domains {

  public ListDomainsResponse listDomains(String accountId) throws DnsimpleException, IOException {
    return new ListDomainsResponse();
  }

  public ListDomainsResponse listDomains(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListDomainsResponse();
  }

  public GetDomainResponse getDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    return new GetDomainResponse();
  }

  public CreateDomainResponse createDomain(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateDomainResponse();
  }

  public DeleteDomainResponse deleteDomain(String accountId, String domainId) throws DnsimpleException, IOException {
    return new DeleteDomainResponse();
  }

  public ResetDomainTokenResponse resetDomainToken(String accountId, String domainId) throws DnsimpleException, IOException {
    return new ResetDomainTokenResponse();
  }

  public ListEmailForwardsResponse listEmailForwards(String accountId, String domainId) throws DnsimpleException, IOException {
    return new ListEmailForwardsResponse();
  }

  public ListEmailForwardsResponse listEmailForwards(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListEmailForwardsResponse();
  }

  public GetEmailForwardResponse getEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException {
    return new GetEmailForwardResponse();
  }

  public CreateEmailForwardResponse createEmailForward(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateEmailForwardResponse();
  }

  public DeleteEmailForwardResponse deleteEmailForward(String accountId, String domainId, String emailForwardId) throws DnsimpleException, IOException {
    return new DeleteEmailForwardResponse();
  }

  public InitiatePushResponse initiatePush(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new InitiatePushResponse();
  }

  public ListPushesResponse listPushes(String accountId, String domainId) throws DnsimpleException, IOException {
    return new ListPushesResponse();
  }

  public ListPushesResponse listPushes(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListPushesResponse();
  }

  public AcceptPushResponse acceptPush(String accountId, String pushId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new AcceptPushResponse();
  }

  public RejectPushResponse rejectPush(String accountId, String pushId) throws DnsimpleException, IOException {
    return new RejectPushResponse();
  }

}


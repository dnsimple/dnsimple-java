package com.dnsimple.endpoints.test;

import com.dnsimple.Accounts;
import com.dnsimple.Contacts;
import com.dnsimple.Certificates;
import com.dnsimple.Domains;
import com.dnsimple.Identity;
import com.dnsimple.Oauth;
import com.dnsimple.Registrar;
import com.dnsimple.Services;
import com.dnsimple.Templates;
import com.dnsimple.Tlds;
import com.dnsimple.VanityNameServers;
import com.dnsimple.Webhooks;
import com.dnsimple.Zones;
import com.dnsimple.endpoints.EndpointAdapter;

public class TestEndpointAdapter implements EndpointAdapter {

  public Accounts accounts() {
    return new AccountsEndpoint();
  }

  public Certificates certificates() {
    return new CertificatesEndpoint();
  }

  public Contacts contacts() {
    return new ContactsEndpoint();
  }

  public Domains domains() {
    return new DomainsEndpoint();
  }

  public Identity identity() {
    return new IdentityEndpoint();
  }

  public Oauth oauth() {
    return new OauthEndpoint();
  }

  public Registrar registrar() {
    return new RegistrarEndpoint();
  }

  public Services services() {
    return new ServicesEndpoint();
  }

  public Templates templates() {
    return new TemplatesEndpoint();
  }

  public Tlds tlds() {
    return new TldsEndpoint();
  }

  public VanityNameServers vanityNameServers() {
    return new VanityNameServersEndpoint();
  }

  public Webhooks webhooks() {
    return new WebhooksEndpoint();
  }

  public Zones zones() {
    return new ZonesEndpoint();
  }

}


package com.dnsimple.endpoints.http;

import com.dnsimple.Accounts;
import com.dnsimple.Certificates;
import com.dnsimple.Collaborators;
import com.dnsimple.Contacts;
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

public class HttpEndpointAdapter implements EndpointAdapter {

  private HttpEndpointClient endpointClient;

  public HttpEndpointAdapter(HttpEndpointClient endpointClient) {
    this.endpointClient = endpointClient;
  }

  public Accounts accounts() {
    return new AccountsEndpoint(endpointClient);
  }

  public Certificates certificates() {
    return new CertificatesEndpoint(endpointClient);
  }

  public Collaborators collaborators() {
    return new CollaboratorsEndpoint(endpointClient);
  }

  public Contacts contacts() {
    return new ContactsEndpoint(endpointClient);
  }

  public Domains domains() {
    return new DomainsEndpoint(endpointClient);
  }

  public Identity identity() {
    return new IdentityEndpoint(endpointClient);
  }

  public Oauth oauth() {
    return new OauthEndpoint(endpointClient);
  }

  public Registrar registrar() {
    return new RegistrarEndpoint(endpointClient);
  }

  public Services services() {
    return new ServicesEndpoint(endpointClient);
  }

  public Templates templates() {
    return new TemplatesEndpoint(endpointClient);
  }

  public Tlds tlds() {
    return new TldsEndpoint(endpointClient);
  }

  public VanityNameServers vanityNameServers() {
    return new VanityNameServersEndpoint(endpointClient);
  }

  public Webhooks webhooks() {
    return new WebhooksEndpoint(endpointClient);
  }

  public Zones zones() {
    return new ZonesEndpoint(endpointClient);
  }

}

package com.dnsimple;

import com.dnsimple.endpoints.HttpEndpointClient;
import com.dnsimple.endpoints.AccountsEndpoint;
import com.dnsimple.endpoints.CertificatesEndpoint;
import com.dnsimple.endpoints.ContactsEndpoint;
import com.dnsimple.endpoints.DomainsEndpoint;
import com.dnsimple.endpoints.IdentityEndpoint;
import com.dnsimple.endpoints.OauthEndpoint;
import com.dnsimple.endpoints.RegistrarEndpoint;
import com.dnsimple.endpoints.ServicesEndpoint;
import com.dnsimple.endpoints.TemplatesEndpoint;
import com.dnsimple.endpoints.TldsEndpoint;
import com.dnsimple.endpoints.VanityNameServersEndpoint;
import com.dnsimple.endpoints.WebhooksEndpoint;
import com.dnsimple.endpoints.ZonesEndpoint;

import com.google.api.client.http.HttpTransport;

/**
 * Instances of the Client handle low-level HTTP calls to the API.
 */
public class Client {

  public final Accounts accounts;
  public final Certificates certificates;
  public final Contacts contacts;
  public final Domains domains;
  public final Identity identity;
  public final Oauth oauth;
  public final Registrar registrar;
  public final Services services;
  public final Templates templates;
  public final Tlds tlds;
  public final VanityNameServers vanityNameServers;
  public final Webhooks webhooks;
  public final Zones zones;

  private HttpEndpointClient endpointClient;

  /**
   * Construct a new API client.
   *
   * Once you have a client instance, use the public properties such as `accounts` or `domains`
   * to communicate with the remote API.
   *
   * For example:
   *
   * Client client = new Client();
   * WhoamiResponse response = client.accounts.whoami();
   */
  public Client() {
    this.endpointClient = new HttpEndpointClient();

    this.accounts = new AccountsEndpoint(endpointClient);
    this.certificates = new CertificatesEndpoint(endpointClient);
    this.contacts = new ContactsEndpoint(endpointClient);
    this.domains = new DomainsEndpoint(endpointClient);
    this.identity = new IdentityEndpoint(endpointClient);
    this.oauth = new OauthEndpoint(endpointClient);
    this.registrar = new RegistrarEndpoint(endpointClient);
    this.services = new ServicesEndpoint(endpointClient);
    this.templates = new TemplatesEndpoint(endpointClient);
    this.tlds = new TldsEndpoint(endpointClient);
    this.vanityNameServers = new VanityNameServersEndpoint(endpointClient);
    this.webhooks = new WebhooksEndpoint(endpointClient);
    this.zones = new ZonesEndpoint(endpointClient);
  }

  /**
   * Set the underlying transport mechanism.
   *
   * This method is primarily used to specify a mocked transport layer during testing.
   *
   * @param transport The transport instance
   */
  public void setTransport(HttpTransport transport) {
    this.endpointClient.setTransport(transport);
  }

}

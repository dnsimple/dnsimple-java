package com.dnsimple;

import com.dnsimple.endpoints.EndpointAdapter;
import com.dnsimple.endpoints.http.HttpEndpointAdapter;
import com.dnsimple.endpoints.http.HttpEndpointClient;
import com.dnsimple.endpoints.http.AccountsEndpoint;
import com.dnsimple.endpoints.http.CertificatesEndpoint;
import com.dnsimple.endpoints.http.ContactsEndpoint;
import com.dnsimple.endpoints.http.DomainsEndpoint;
import com.dnsimple.endpoints.http.IdentityEndpoint;
import com.dnsimple.endpoints.http.OauthEndpoint;
import com.dnsimple.endpoints.http.RegistrarEndpoint;
import com.dnsimple.endpoints.http.ServicesEndpoint;
import com.dnsimple.endpoints.http.TemplatesEndpoint;
import com.dnsimple.endpoints.http.TldsEndpoint;
import com.dnsimple.endpoints.http.VanityNameServersEndpoint;
import com.dnsimple.endpoints.http.WebhooksEndpoint;
import com.dnsimple.endpoints.http.ZonesEndpoint;

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
   * Construct a new API client with the default HTTP endpoint adapter.
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
    EndpointAdapter adapter = new HttpEndpointAdapter(endpointClient);

    this.accounts = adapter.accounts();
    this.certificates = adapter.certificates();
    this.contacts = adapter.contacts();
    this.domains = adapter.domains();
    this.identity = adapter.identity();
    this.oauth = adapter.oauth();
    this.registrar = adapter.registrar();
    this.services = adapter.services();
    this.templates = adapter.templates();
    this.tlds = adapter.tlds();
    this.vanityNameServers = adapter.vanityNameServers();
    this.webhooks = adapter.webhooks();
    this.zones = adapter.zones();
  }

  /**
   * Construct a new API client with the given endpoint adapter.
   *
   * Once you have a client instance, use the public properties such as `accounts` or `domains`
   * to communicate with the remote API.
   *
   * For example:
   *
   * EndpointAdapter adapter = new TestEndpointAdapter();
   * Client client = new Client(adapter);
   * WhoamiResponse response = client.accounts.whoami();
   *
   * @param adapter The endpoint adapter to use in the client.
   */
  public Client(EndpointAdapter adapter) {
    this.accounts = adapter.accounts();
    this.certificates = adapter.certificates();
    this.contacts = adapter.contacts();
    this.domains = adapter.domains();
    this.identity = adapter.identity();
    this.oauth = adapter.oauth();
    this.registrar = adapter.registrar();
    this.services = adapter.services();
    this.templates = adapter.templates();
    this.tlds = adapter.tlds();
    this.vanityNameServers = adapter.vanityNameServers();
    this.webhooks = adapter.webhooks();
    this.zones = adapter.zones();
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

  /**
   * Set the access token to use for the client instance.
   *
   * @param accessToken The access token string
   */
  public void setAccessToken(String accessToken) {
    this.endpointClient.setAccessToken(accessToken);
  }

}

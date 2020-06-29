package com.dnsimple;

import com.dnsimple.endpoints.EndpointAdapter;
import com.dnsimple.endpoints.http.GoogleHttpEndpointClient;
import com.dnsimple.endpoints.http.HttpEndpointAdapter;
import com.dnsimple.endpoints.http.HttpEndpointClient;

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
   * <p>
   * Once you have a client instance, use the public properties such as `accounts` or `domains`
   * to communicate with the remote API.
   * <p>
   * For example:
   * <p>
   * Client client = new Client();
   * WhoamiResponse response = client.accounts.whoami();
   */
  public Client() {
    this.endpointClient = new GoogleHttpEndpointClient();
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

  public Client(GoogleHttpEndpointClient endpointClient) {
    this.endpointClient = endpointClient;
    EndpointAdapter adapter = new HttpEndpointAdapter(this.endpointClient);

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
   * <p>
   * Once you have a client instance, use the public properties such as `accounts` or `domains`
   * to communicate with the remote API.
   * <p>
   * For example:
   * <p>
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
}

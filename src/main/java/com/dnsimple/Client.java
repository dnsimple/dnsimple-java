package com.dnsimple;

import com.dnsimple.endpoints.http.*;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Instances of the Client handle low-level HTTP calls to the API.
 */
public class Client {
    public static final URL PRODUCTION_API_BASE = url("https://api.dnsimple.com");
    public static final URL SANDBOX_API_BASE = url("https://api.sandbox.dnsimple.com");
    private final HttpEndpointClient endpointClient;
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

    private Client(HttpEndpointClient endpointClient, Accounts accounts, Certificates certificates, Contacts contacts, Domains domains, Identity identity, Oauth oauth, Registrar registrar, Services services, Templates templates, Tlds tlds, VanityNameServers vanityNameServers, Webhooks webhooks, Zones zones) {
        this.endpointClient = endpointClient;
        this.accounts = accounts;
        this.certificates = certificates;
        this.contacts = contacts;
        this.domains = domains;
        this.identity = identity;
        this.oauth = oauth;
        this.registrar = registrar;
        this.services = services;
        this.templates = templates;
        this.tlds = tlds;
        this.vanityNameServers = vanityNameServers;
        this.webhooks = webhooks;
        this.zones = zones;
    }

    public static Client of(HttpRequestFactory requestFactory, URL apiBase) {
        HttpEndpointClient endpointClient = new HttpEndpointClient(requestFactory, apiBase);
        return new Client(
                endpointClient,
                new AccountsEndpoint(endpointClient),
                new CertificatesEndpoint(endpointClient),
                new ContactsEndpoint(endpointClient),
                new DomainsEndpoint(endpointClient),
                new IdentityEndpoint(endpointClient),
                new OauthEndpoint(endpointClient),
                new RegistrarEndpoint(endpointClient),
                new ServicesEndpoint(endpointClient),
                new TemplatesEndpoint(endpointClient),
                new TldsEndpoint(endpointClient),
                new VanityNameServersEndpoint(endpointClient),
                new WebhooksEndpoint(endpointClient),
                new ZonesEndpoint(endpointClient)
        );
    }

    private static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

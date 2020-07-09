package com.dnsimple;

import com.dnsimple.endpoints.http.*;
import com.dnsimple.endpoints.http.java11.Java11HttpRequestFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Instances of the Client handle low-level HTTP calls to the API.
 */
public class Client {
    private static final URL PRODUCTION_API_BASE = url("https://api.dnsimple.com");
    private static final URL SANDBOX_API_BASE = url("https://api.sandbox.dnsimple.com");
    private static final String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;
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

    private static Client of(HttpRequestFactory httpRequestFactory, URL apiBase, String userAgent, Optional<String> accessToken) {
        HttpEndpointClient endpointClient = new HttpEndpointClient(httpRequestFactory, apiBase, userAgent, accessToken);
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

    public Client setAccessToken(String accessToken) {
        endpointClient.setAccessToken(accessToken);
        return this;
    }

    private static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Builder class to obtain Client instances. By default, builders will
     * provide a Client instance pointing to the production environment, a default
     * user agent header, no access token, and using the default Java11 native HTTP
     * client implementation of HttpRequestFactory.
     *
     * @see HttpRequestFactory
     */
    public static class Builder {
        private URL apiBase = PRODUCTION_API_BASE;
        private HttpRequestFactory httpRequestFactory = new Java11HttpRequestFactory();
        private Optional<String> extraUserAgent = Optional.empty();
        private Optional<String> accessToken = Optional.empty();

        /**
         * Use DNSimple API's sandbox environment
         *
         * @return this Builder object
         */
        public Builder sandbox() {
            apiBase = SANDBOX_API_BASE;
            return this;
        }

        /**
         * Use a custom API base URL
         *
         * @param url
         * @return this Builder object
         */
        public Builder apiBase(String url) {
            apiBase = url(url);
            return this;
        }

        /**
         * Use a custom API base URL
         *
         * @param url
         * @return this Builder object
         */
        public Builder apiBase(URL url) {
            apiBase = url;
            return this;
        }

        /**
         * Use a custom HTTP request factory object
         *
         * @param factory
         * @return this Builder object
         */
        public Builder httpRequestFactory(HttpRequestFactory factory) {
            httpRequestFactory = factory;
            return this;
        }

        /**
         * Set an extra user agent that will be prefixed to the default
         *
         * @param userAgent
         * @return this Builder object
         */
        public Builder extraUserAgent(String userAgent) {
            extraUserAgent = Optional.of(userAgent);
            return this;
        }

        /**
         * Set a the access token for all API requests. You can set it later
         * directly on the Client object as well.
         *
         * @param accessToken
         * @return this Builder object
         */
        public Builder accessToken(String accessToken) {
            this.accessToken = Optional.of(accessToken);
            return this;
        }

        /**
         * Builds a Client object with the configured values and returns it.
         *
         * @return the Client object
         */
        public Client build() {
            String userAgent = String.join(" ", buildUserAgents(extraUserAgent));
            return Client.of(httpRequestFactory, apiBase, userAgent, accessToken);
        }

        private static List<String> buildUserAgents(Optional<String> userAgent) {
            List<String> userAgents = new ArrayList<>();
            userAgent.ifPresent(userAgents::add);
            userAgents.add(DEFAULT_USER_AGENT);
            return userAgents;
        }
    }
}

package com.dnsimple.endpoints;

import com.dnsimple.*;

public interface EndpointAdapter {
    public Accounts accounts();

    public Certificates certificates();

    public Contacts contacts();

    public Domains domains();

    public Identity identity();

    public Oauth oauth();

    public Registrar registrar();

    public Services services();

    public Templates templates();

    public Tlds tlds();

    public VanityNameServers vanityNameServers();

    public Webhooks webhooks();

    public Zones zones();
}

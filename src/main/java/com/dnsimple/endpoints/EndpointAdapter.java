package com.dnsimple.endpoints;

import com.dnsimple.*;

public interface EndpointAdapter {
    Accounts accounts();

    Certificates certificates();

    Contacts contacts();

    Domains domains();

    Identity identity();

    Oauth oauth();

    Registrar registrar();

    Services services();

    Templates templates();

    Tlds tlds();

    VanityNameServers vanityNameServers();

    Webhooks webhooks();

    Zones zones();
}

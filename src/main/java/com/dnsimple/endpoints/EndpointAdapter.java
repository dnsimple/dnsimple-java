package com.dnsimple.endpoints;

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

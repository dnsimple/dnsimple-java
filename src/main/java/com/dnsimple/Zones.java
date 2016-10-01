package com.dnsimple;

import java.io.IOException;
import java.util.Map;

import com.dnsimple.response.ListZonesResponse;
import com.dnsimple.response.GetZoneResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple Zones API.
 *
 * @see https://developer.dnsimple.com/v2/zones
 */
public class Zones {
  private Client client;

  protected Zones(Client client) {
    this.client = client;
  }

  // Domains

  /**
   * Lists the zones in the account.
   *
   * @see https://developer.dnsimple.com/v2/zones/#list
   *
   * @param accountId The account ID
   * @return The list zones response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListZonesResponse listZones(String accountId) throws DnsimpleException, IOException {
    return listZones(accountId, null);
  }

  /**
   * Lists the zones in the account.
   *
   * @see https://developer.dnsimple.com/v2/zones/#list
   *
   * @param accountId The account ID
   * @param options A Map of options to pass to the zones API
   * @return The list zones response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListZonesResponse listZones(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones", options);
    return (ListZonesResponse)client.parseResponse(response, ListZonesResponse.class);
  }

  /**
   * Get a specific zone associated to an account using the zone's name or ID.
   *
   * @see https://developer.dnsimple.com/v2/zones/#get
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @return The get zone response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetZoneResponse getZone(String accountId, String zoneId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId);
    return (GetZoneResponse)client.parseResponse(response, GetZoneResponse.class);
  }

}

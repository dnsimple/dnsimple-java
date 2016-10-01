package com.dnsimple;

import java.io.IOException;
import java.util.Map;

import com.dnsimple.response.ListZonesResponse;
import com.dnsimple.response.GetZoneResponse;
import com.dnsimple.response.GetZoneFileResponse;

import com.dnsimple.response.ListZoneRecordsResponse;
import com.dnsimple.response.GetZoneRecordResponse;
import com.dnsimple.response.CreateZoneRecordResponse;
import com.dnsimple.response.UpdateZoneRecordResponse;
import com.dnsimple.response.DeleteZoneRecordResponse;

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

  /**
   * Get the zone file associated to an account using the zone's name or ID.
   *
   * @see https://developer.dnsimple.com/v2/zones/#get-file
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @return The get zone file response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetZoneFileResponse getZoneFile(String accountId, String zoneId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId + "/file");
    return (GetZoneFileResponse)client.parseResponse(response, GetZoneFileResponse.class);
  }

  /**
   * Lists the records in the zone.
   *
   * @see https://developer.dnsimple.com/v2/zones/records/#list
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @return The list zone records response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId) throws DnsimpleException, IOException {
    return listZoneRecords(accountId, zoneId, null);
  }

  /**
   * Lists the records in the zone.
   *
   * @see https://developer.dnsimple.com/v2/zones/records/#list
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @param options A Map of options to pass to the zones API
   * @return The list zone records response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId + "/records", options);
    return (ListZoneRecordsResponse)client.parseResponse(response, ListZoneRecordsResponse.class);
  }

  /**
   * Get a specific record associated to a zone using the zone's name or ID.
   *
   * @see https://developer.dnsimple.com/v2/zones/records/#get
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @param recordId The zone record ID
   * @return The get zone record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetZoneRecordResponse getZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId + "/records/" + recordId);
    return (GetZoneRecordResponse)client.parseResponse(response, GetZoneRecordResponse.class);
  }

  /**
   * Create a record in a zone.
   *
   * @see https://developer.dnsimple.com/v2/zones/records/#create
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @param attributes The zone attributes
   * @return The create zone record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateZoneRecordResponse createZoneRecord(String accountId, String zoneId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/zones/" + zoneId + "/records", attributes);
    return (CreateZoneRecordResponse)client.parseResponse(response, CreateZoneRecordResponse.class);
  }

  /**
   * Update a record in a zone.
   *
   * @see https://developer.dnsimple.com/v2/zones/records/#update
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @param recordId The zone record ID
   * @param attributes The zone attributes
   * @return The update zone record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public UpdateZoneRecordResponse updateZoneRecord(String accountId, String zoneId, String recordId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.patch(accountId + "/zones/" + zoneId + "/records/" + recordId, attributes);
    return (UpdateZoneRecordResponse)client.parseResponse(response, UpdateZoneRecordResponse.class);
  }

  /**
   * Delete a record from a zone.
   *
   * @see https://developer.dnsimple.com/v2/zones/records/#delete
   * @param accountId The account ID
   * @param zoneId The zone name or ID
   * @param recordId The zone record ID
   * @return The delete zone record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteZoneRecordResponse deleteZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/zones/" + zoneId + "/records/" + recordId);
    return (DeleteZoneRecordResponse)client.parseResponse(response, DeleteZoneRecordResponse.class);
  }
}

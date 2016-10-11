package com.dnsimple.endpoints.http;

import com.dnsimple.Zones;
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

import java.io.IOException;
import java.util.Map;


public class ZonesEndpoint implements Zones {
  private HttpEndpointClient client;

  public ZonesEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListZonesResponse listZones(String accountId) throws DnsimpleException, IOException {
    return listZones(accountId, null);
  }

  public ListZonesResponse listZones(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones", options);
    return (ListZonesResponse)client.parseResponse(response, ListZonesResponse.class);
  }

  public GetZoneResponse getZone(String accountId, String zoneId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId);
    return (GetZoneResponse)client.parseResponse(response, GetZoneResponse.class);
  }

  public GetZoneFileResponse getZoneFile(String accountId, String zoneId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId + "/file");
    return (GetZoneFileResponse)client.parseResponse(response, GetZoneFileResponse.class);
  }

  public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId) throws DnsimpleException, IOException {
    return listZoneRecords(accountId, zoneId, null);
  }

  public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId + "/records", options);
    return (ListZoneRecordsResponse)client.parseResponse(response, ListZoneRecordsResponse.class);
  }

  public GetZoneRecordResponse getZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/zones/" + zoneId + "/records/" + recordId);
    return (GetZoneRecordResponse)client.parseResponse(response, GetZoneRecordResponse.class);
  }

  public CreateZoneRecordResponse createZoneRecord(String accountId, String zoneId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/zones/" + zoneId + "/records", attributes);
    return (CreateZoneRecordResponse)client.parseResponse(response, CreateZoneRecordResponse.class);
  }

  public UpdateZoneRecordResponse updateZoneRecord(String accountId, String zoneId, String recordId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.patch(accountId + "/zones/" + zoneId + "/records/" + recordId, attributes);
    return (UpdateZoneRecordResponse)client.parseResponse(response, UpdateZoneRecordResponse.class);
  }

  public DeleteZoneRecordResponse deleteZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/zones/" + zoneId + "/records/" + recordId);
    return (DeleteZoneRecordResponse)client.parseResponse(response, DeleteZoneRecordResponse.class);
  }
}

package com.dnsimple.endpoints.test;

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

import java.io.IOException;
import java.util.Map;

public class ZonesEndpoint implements Zones {

  public ListZonesResponse listZones(String accountId) throws DnsimpleException, IOException {
    return new ListZonesResponse();
  }

  public ListZonesResponse listZones(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListZonesResponse();
  }

  public GetZoneResponse getZone(String accountId, String zoneId) throws DnsimpleException, IOException {
    return new GetZoneResponse();
  }

  public GetZoneFileResponse getZoneFile(String accountId, String zoneId) throws DnsimpleException, IOException {
    return new GetZoneFileResponse();
  }

  public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId) throws DnsimpleException, IOException {
    return new ListZoneRecordsResponse();
  }

  public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListZoneRecordsResponse();
  }

  public GetZoneRecordResponse getZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
    return new GetZoneRecordResponse();
  }

  public CreateZoneRecordResponse createZoneRecord(String accountId, String zoneId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateZoneRecordResponse();
  }

  public UpdateZoneRecordResponse updateZoneRecord(String accountId, String zoneId, String recordId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new UpdateZoneRecordResponse();
  }

  public DeleteZoneRecordResponse deleteZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
    return new DeleteZoneRecordResponse();
  }

}


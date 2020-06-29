package com.dnsimple.endpoints.http;

import com.dnsimple.Zones;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

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

    public ListZonesResponse listZones(String accountId, Map<String, Object> options) throws DnsimpleException, IOException {
        return (ListZonesResponse) client.get(accountId + "/zones", options, ListZonesResponse.class);
    }

    public GetZoneResponse getZone(String accountId, String zoneId) throws DnsimpleException, IOException {
        return (GetZoneResponse) client.get(accountId + "/zones/" + zoneId, null, GetZoneResponse.class);
    }

    public GetZoneFileResponse getZoneFile(String accountId, String zoneId) throws DnsimpleException, IOException {
        return (GetZoneFileResponse) client.get(accountId + "/zones/" + zoneId + "/file", null, GetZoneFileResponse.class);
    }

    public CheckZoneDistributionResponse checkZoneDistribution(String accountId, String zoneId) throws DnsimpleException, IOException {
        return (CheckZoneDistributionResponse) client.get(accountId + "/zones/" + zoneId + "/distribution", null, CheckZoneDistributionResponse.class);
    }

    public CheckZoneDistributionResponse checkZoneRecordDistribution(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
        return (CheckZoneDistributionResponse) client.get(accountId + "/zones/" + zoneId + "/records/" + recordId + "/distribution", null, CheckZoneDistributionResponse.class);
    }

    public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId) throws DnsimpleException, IOException {
        return listZoneRecords(accountId, zoneId, null);
    }

    public ListZoneRecordsResponse listZoneRecords(String accountId, String zoneId, Map<String, Object> options) throws DnsimpleException, IOException {
        return (ListZoneRecordsResponse) client.get(accountId + "/zones/" + zoneId + "/records", options, ListZoneRecordsResponse.class);
    }

    public GetZoneRecordResponse getZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
        return (GetZoneRecordResponse) client.get(accountId + "/zones/" + zoneId + "/records/" + recordId, null, GetZoneRecordResponse.class);
    }

    public CreateZoneRecordResponse createZoneRecord(String accountId, String zoneId, Map<String, Object> attributes) throws DnsimpleException, IOException {
        return (CreateZoneRecordResponse) client.post(accountId + "/zones/" + zoneId + "/records", attributes, null, CreateZoneRecordResponse.class);
    }

    public UpdateZoneRecordResponse updateZoneRecord(String accountId, String zoneId, String recordId, Map<String, Object> attributes) throws DnsimpleException, IOException {
        return (UpdateZoneRecordResponse) client.patch(accountId + "/zones/" + zoneId + "/records/" + recordId, attributes, null, UpdateZoneRecordResponse.class);
    }

    public DeleteZoneRecordResponse deleteZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException {
        return (DeleteZoneRecordResponse) client.delete(accountId + "/zones/" + zoneId + "/records/" + recordId, null, DeleteZoneRecordResponse.class);
    }
}

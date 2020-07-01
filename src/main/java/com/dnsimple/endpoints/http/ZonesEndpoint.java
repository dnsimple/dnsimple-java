package com.dnsimple.endpoints.http;

import com.dnsimple.Zones;
import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.data.ZoneRecord;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public class ZonesEndpoint implements Zones {
    private final HttpEndpointClient client;

    public ZonesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Zone> listZones(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listZones(accountId, null);
    }

    public PaginatedResponse<Zone> listZones(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.getPage(accountId + "/zones", options, Zone.class);
    }

    public SimpleResponse<Zone> getZone(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/zones/" + zoneId, null, Zone.class);
    }

    public SimpleResponse<ZoneFile> getZoneFile(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/zones/" + zoneId + "/file", null, ZoneFile.class);
    }

    public SimpleResponse<ZoneDistribution> checkZoneDistribution(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/zones/" + zoneId + "/distribution", null, ZoneDistribution.class);
    }

    public SimpleResponse<ZoneDistribution> checkZoneRecordDistribution(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/zones/" + zoneId + "/records/" + recordId + "/distribution", null, ZoneDistribution.class);
    }

    public PaginatedResponse<ZoneRecord> listZoneRecords(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException {
        return listZoneRecords(accountId, zoneId, null);
    }

    public PaginatedResponse<ZoneRecord> listZoneRecords(String accountId, String zoneId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.getPage(accountId + "/zones/" + zoneId + "/records", options, ZoneRecord.class);
    }

    public SimpleResponse<ZoneRecord> getZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/zones/" + zoneId + "/records/" + recordId, null, ZoneRecord.class);
    }

    public SimpleResponse<ZoneRecord> createZoneRecord(String accountId, String zoneId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/zones/" + zoneId + "/records", attributes, null, ZoneRecord.class);
    }

    public SimpleResponse<ZoneRecord> updateZoneRecord(String accountId, String zoneId, String recordId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.patchSimple(accountId + "/zones/" + zoneId + "/records/" + recordId, attributes, null, ZoneRecord.class);
    }

    public EmptyResponse deleteZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/zones/" + zoneId + "/records/" + recordId, null);
    }
}

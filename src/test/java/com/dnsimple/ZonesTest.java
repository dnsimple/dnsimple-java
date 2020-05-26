package com.dnsimple;

import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.Pagination;
import com.dnsimple.request.Filter;
import com.dnsimple.response.ListZonesResponse;
import com.dnsimple.response.GetZoneResponse;
import com.dnsimple.response.GetZoneFileResponse;
import com.dnsimple.response.CheckZoneDistributionResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class ZonesTest extends DnsimpleTestBase {

  @Test
  public void testListZonesSupportsPagination() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/zones?page=1");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.zones.listZones(accountId, options);
  }

  @Test
  public void testListZonesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/zones?foo=bar");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.zones.listZones(accountId, options);
  }

  @Test
  public void testListDomainsSupportsSorting() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/zones?sort=expires_on%3Aasc");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "expires_on:asc");
    client.zones.listZones(accountId, options);
  }

  @Test
  public void testListZonesSupportsFiltering() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/zones?name_like=example");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("filter", new Filter("name_like", "example"));
    client.zones.listZones(accountId, options);
  }

  @Test
  public void testListZonesProducesZoneList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listZones/success.http");
    Client client = new Client();

    String accountId = "1";

    ListZonesResponse response = client.zones.listZones(accountId);

    List<Zone> zones = response.getData();
    assertEquals(2, zones.size());
    assertEquals(1, zones.get(0).getId().intValue());
  }

  @Test
  public void testListZonesExposesPaginationInfo() throws DnsimpleException, IOException {
    server.stubFixtureAt("listZones/success.http");
    Client client = new Client();

    String accountId = "1";

    ListZonesResponse response = client.zones.listZones(accountId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetZone() throws DnsimpleException, IOException {
    server.stubFixtureAt("getZone/success.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    GetZoneResponse response = client.zones.getZone(accountId, zoneId);

    Zone zone = response.getData();
    assertEquals(1, zone.getId().intValue());
    assertEquals(1010, zone.getAccountId().intValue());
    assertEquals("example-alpha.com", zone.getName());
    assertEquals("2015-04-23T07:40:03Z", zone.getCreatedAt());
    assertEquals("2015-04-23T07:40:03Z", zone.getUpdatedAt());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetZoneWhenZoneNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-zone.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    client.zones.getZone(accountId, zoneId);
  }

  @Test
  public void testGetZoneFile() throws DnsimpleException, IOException {
    server.stubFixtureAt("getZoneFile/success.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    GetZoneFileResponse response = client.zones.getZoneFile(accountId, zoneId);

    ZoneFile zoneFile = response.getData();
    assertEquals("$ORIGIN example.com.\n$TTL 1h\nexample.com. 3600 IN SOA ns1.dnsimple.com. admin.dnsimple.com. 1453132552 86400 7200 604800 300\nexample.com. 3600 IN NS ns1.dnsimple.com.\nexample.com. 3600 IN NS ns2.dnsimple.com.\nexample.com. 3600 IN NS ns3.dnsimple.com.\nexample.com. 3600 IN NS ns4.dnsimple.com.\n", zoneFile.getZone());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetZoneFileWhenZoneNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-zone.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    client.zones.getZoneFile(accountId, zoneId);
  }

  @Test
  public void testCheckZoneDistribution() throws DnsimpleException, IOException {
    server.stubFixtureAt("checkZoneDistribution/success.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    CheckZoneDistributionResponse response = client.zones.checkZoneDistribution(accountId, zoneId);

    ZoneDistribution zoneDistribution = response.getData();
    assertEquals(true, zoneDistribution.isDistributed());
  }

  @Test
  public void testCheckZoneDistributionNotDistributed() throws DnsimpleException, IOException {
    server.stubFixtureAt("checkZoneDistribution/failure.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    CheckZoneDistributionResponse response = client.zones.checkZoneDistribution(accountId, zoneId);

    ZoneDistribution zoneDistribution = response.getData();
    assertEquals(false, zoneDistribution.isDistributed());
  }

  @Test(expected=DnsimpleException.class)
  public void testCheckZoneDistributionFailedCheck() throws DnsimpleException, IOException {
    server.stubFixtureAt("checkZoneDistribution/error.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    CheckZoneDistributionResponse response = client.zones.checkZoneDistribution(accountId, zoneId);

    ZoneDistribution zoneDistribution = response.getData();
    assertEquals(false, zoneDistribution.isDistributed());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testCheckZoneDistributionWhenZoneNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-zone.http");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";

    client.zones.checkZoneDistribution(accountId, zoneId);
  }

  @Test
  public void testCheckZoneRecordDistribution() throws DnsimpleException, IOException {
    server.stubFixtureAt("checkZoneRecordDistribution/success.http");
    Client client = new Client();

    String accountId = "1010";
    String zoneId = "example.com";
    String recordId = "1";

    CheckZoneDistributionResponse response = client.zones.checkZoneRecordDistribution(accountId, zoneId, recordId);

    ZoneDistribution zoneDistribution = response.getData();
    assertEquals(true, zoneDistribution.isDistributed());
  }

  @Test
  public void testCheckZoneRecordDistributionNotDistributed() throws DnsimpleException, IOException {
    server.stubFixtureAt("checkZoneRecordDistribution/failure.http");
    Client client = new Client();

    String accountId = "1010";
    String zoneId = "example.com";
    String recordId = "1";

    CheckZoneDistributionResponse response = client.zones.checkZoneRecordDistribution(accountId, zoneId, accountId);

    ZoneDistribution zoneDistribution = response.getData();
    assertEquals(false, zoneDistribution.isDistributed());
  }

  @Test(expected=DnsimpleException.class)
  public void testCheckZoneRecordDistributionFailedCheck() throws DnsimpleException, IOException {
    server.stubFixtureAt("checkZoneRecordDistribution/error.http");
    Client client = new Client();

    String accountId = "1010";
    String zoneId = "example.com";
    String recordId = "1";

    CheckZoneDistributionResponse response = client.zones.checkZoneRecordDistribution(accountId, zoneId, accountId);

    ZoneDistribution zoneDistribution = response.getData();
    assertEquals(false, zoneDistribution.isDistributed());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testCheckZoneRecordDistributionWhenZoneNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-zone.http");
    Client client = new Client();

    String accountId = "1010";
    String zoneId = "example.com";
    String recordId = "1";

    client.zones.checkZoneRecordDistribution(accountId, zoneId, accountId);
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testCheckZoneRecordDistributionWhenZoneRecordNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-record.http");
    Client client = new Client();

    String accountId = "1010";
    String zoneId = "example.com";
    String recordId = "1";

    client.zones.checkZoneRecordDistribution(accountId, zoneId, accountId);
  }
}

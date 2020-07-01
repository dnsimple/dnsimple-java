package com.dnsimple;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.*;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import com.dnsimple.data.Zone;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.CheckZoneDistributionResponse;
import com.dnsimple.response.GetZoneFileResponse;
import com.dnsimple.response.ListZonesResponse;
import com.dnsimple.tools.HttpMethod;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ZonesTest extends DnsimpleTestBase {

  @Test
  public void testListZonesSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
    client.zones.listZones("1", singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?page=1"));
  }

  @Test
  public void testListZonesSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
    client.zones.listZones("1", singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?foo=bar"));
  }

  @Test
  public void testListDomainsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
    client.zones.listZones("1", singletonMap("sort", "expires_on:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?sort=expires_on%3Aasc"));
  }

  @Test
  public void testListZonesSupportsFiltering() throws DnsimpleException, IOException, InterruptedException {
    Map<String, Object> options = new HashMap<>();
    options.put("filter", new Filter("name_like", "example"));
    client.zones.listZones("1", options);
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?name_like=example"));
  }

  @Test
  public void testListZonesProducesZoneList() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("listZones/success.http");

    List<Zone> zones = client.zones.listZones("1").getData();
    assertThat(zones, hasSize(2));
    assertThat(zones.get(0).getId(), is(1));
  }

  @Test
  public void testListZonesExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("listZones/success.http");

    ListZonesResponse response = client.zones.listZones("1");
    assertThat(response.getPagination().getCurrentPage(), is(1));
  }

  @Test
  public void testGetZone() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("getZone/success.http");

    Zone zone = client.zones.getZone("1", "example.com").getData();
    assertThat(zone.getId(), is(1));
    assertThat(zone.getAccountId(), is(1010));
    assertThat(zone.getName(), is("example-alpha.com"));
    assertThat(zone.getCreatedAt(), is("2015-04-23T07:40:03Z"));
    assertThat(zone.getUpdatedAt(), is("2015-04-23T07:40:03Z"));
  }

  @Test
  public void testGetZoneWhenZoneNotFound() {
    server.stubFixtureAt("notfound-zone.http");

    assertThat(() -> client.zones.getZone("1", "example.com"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testGetZoneFile() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("getZoneFile/success.http");

    GetZoneFileResponse response = client.zones.getZoneFile("1", "example.com");
    assertThat(response.getData().getZone(), is("" +
        "$ORIGIN example.com.\n" +
        "$TTL 1h\n" +
        "example.com. 3600 IN SOA ns1.dnsimple.com. admin.dnsimple.com. 1453132552 86400 7200 604800 300\n" +
        "example.com. 3600 IN NS ns1.dnsimple.com.\n" +
        "example.com. 3600 IN NS ns2.dnsimple.com.\n" +
        "example.com. 3600 IN NS ns3.dnsimple.com.\n" +
        "example.com. 3600 IN NS ns4.dnsimple.com.\n" +
        ""));
  }

  @Test
  public void testGetZoneFileWhenZoneNotFound() {
    server.stubFixtureAt("notfound-zone.http");

    assertThat(() -> client.zones.getZoneFile("1", "example.com"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testCheckZoneDistribution() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("checkZoneDistribution/success.http");

    CheckZoneDistributionResponse response = client.zones.checkZoneDistribution("1", "example.com");
    assertThat(response.getData().isDistributed(), is(true));
  }

  @Test
  public void testCheckZoneDistributionNotDistributed() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("checkZoneDistribution/failure.http");

    CheckZoneDistributionResponse response = client.zones.checkZoneDistribution("1", "example.com");
    assertThat(response.getData().isDistributed(), is(false));
  }

  @Test
  public void testCheckZoneDistributionFailedCheck() {
    server.stubFixtureAt("checkZoneDistribution/error.http");

    assertThat(() -> client.zones.checkZoneDistribution("1", "example.com"),
        thrownException(is(instanceOf(DnsimpleException.class))));
  }

  @Test
  public void testCheckZoneDistributionWhenZoneNotFound() {
    server.stubFixtureAt("notfound-zone.http");

    assertThat(() -> client.zones.checkZoneDistribution("1", "example.com"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testCheckZoneRecordDistribution() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("checkZoneRecordDistribution/success.http");

    CheckZoneDistributionResponse response = client.zones.checkZoneRecordDistribution("1010", "example.com", "1");
    assertThat(response.getData().isDistributed(), is(true));
  }

  @Test
  public void testCheckZoneRecordDistributionNotDistributed() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("checkZoneRecordDistribution/failure.http");

    CheckZoneDistributionResponse response = client.zones.checkZoneRecordDistribution("1010", "example.com", "1010");
    assertThat(response.getData().isDistributed(), is(false));
  }

  @Test
  public void testCheckZoneRecordDistributionFailedCheck() {
    server.stubFixtureAt("checkZoneRecordDistribution/error.http");

    assertThat(() -> client.zones.checkZoneRecordDistribution("1010", "example.com", "1010"),
        thrownException(is(instanceOf(DnsimpleException.class))));
  }

  @Test
  public void testCheckZoneRecordDistributionWhenZoneNotFound() {
    server.stubFixtureAt("notfound-zone.http");

    assertThat(() -> client.zones.checkZoneRecordDistribution("1010", "example.com", "1010"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testCheckZoneRecordDistributionWhenZoneRecordNotFound() {
    server.stubFixtureAt("notfound-record.http");

    assertThat(() -> client.zones.checkZoneRecordDistribution("1010", "example.com", "1010"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }
}

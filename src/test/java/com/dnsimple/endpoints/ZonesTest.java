package com.dnsimple.endpoints;

import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.dnsimple.http.HttpMethod.GET;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZonesTest extends DnsimpleTestBase {
    @Test
    public void testListZonesSupportsPagination() {
        client.zones.listZones(1, ListOptions.empty().page(1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?page=1"));
    }

    @Test
    public void testListZonesSupportsExtraRequestOptions() {
        client.zones.listZones(1, ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?foo=bar"));
    }

    @Test
    public void testListDomainsSupportsSorting() {
        client.zones.listZones(1, ListOptions.empty().sortAsc("expires_on"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?sort=expires_on%3Aasc"));
    }

    @Test
    public void testListZonesSupportsFiltering() {
        client.zones.listZones(1, ListOptions.empty().filter("name_like", "example"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones?name_like=example"));
    }

    @Test
    public void testListZonesProducesZoneList() {
        server.stubFixtureAt("listZones/success.http");
        List<Zone> zones = client.zones.listZones(1).getData();
        assertThat(zones, hasSize(2));
        assertThat(zones.get(0).getId(), is(1L));
    }

    @Test
    public void testListZonesExposesPaginationInfo() {
        server.stubFixtureAt("listZones/success.http");
        PaginatedResponse<Zone> response = client.zones.listZones(1);
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetZone() {
        server.stubFixtureAt("getZone/success.http");
        Zone zone = client.zones.getZone(1, "example.com").getData();
        assertThat(zone.getId(), is(1L));
        assertThat(zone.getAccountId(), is(1010L));
        assertThat(zone.getName(), is("example-alpha.com"));
        assertThat(zone.isReverse(), is(false));
        assertThat(zone.getCreatedAt(), is(OffsetDateTime.of(2015, 4, 23, 7, 40, 3, 0, ZoneOffset.UTC)));
        assertThat(zone.getUpdatedAt(), is(OffsetDateTime.of(2015, 4, 23, 7, 40, 3, 0, ZoneOffset.UTC)));
    }

    @Test
    public void testGetZoneWhenZoneNotFound() {
        server.stubFixtureAt("notfound-zone.http");
        assertThat(() -> client.zones.getZone(1, "example.com"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testGetZoneFile() {
        server.stubFixtureAt("getZoneFile/success.http");
        SimpleResponse<ZoneFile> response = client.zones.getZoneFile(1, "example.com");
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
        assertThat(() -> client.zones.getZoneFile(1, "example.com"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCheckZoneDistribution() {
        server.stubFixtureAt("checkZoneDistribution/success.http");
        SimpleResponse<ZoneDistribution> response = client.zones.checkZoneDistribution(1, "example.com");
        assertThat(response.getData().isDistributed(), is(true));
    }

    @Test
    public void testCheckZoneDistributionNotDistributed() {
        server.stubFixtureAt("checkZoneDistribution/failure.http");
        SimpleResponse<ZoneDistribution> response = client.zones.checkZoneDistribution(1, "example.com");
        assertThat(response.getData().isDistributed(), is(false));
    }

    @Test
    public void testCheckZoneDistributionFailedCheck() {
        server.stubFixtureAt("checkZoneDistribution/error.http");
        assertThat(() -> client.zones.checkZoneDistribution(1, "example.com"),
                thrownException(is(instanceOf(DnsimpleException.class))));
    }

    @Test
    public void testCheckZoneDistributionWhenZoneNotFound() {
        server.stubFixtureAt("notfound-zone.http");
        assertThat(() -> client.zones.checkZoneDistribution(1, "example.com"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCheckZoneRecordDistribution() {
        server.stubFixtureAt("checkZoneRecordDistribution/success.http");
        SimpleResponse<ZoneDistribution> response = client.zones.checkZoneRecordDistribution(1010, "example.com", 1);
        assertThat(response.getData().isDistributed(), is(true));
    }

    @Test
    public void testCheckZoneRecordDistributionNotDistributed() {
        server.stubFixtureAt("checkZoneRecordDistribution/failure.http");
        SimpleResponse<ZoneDistribution> response = client.zones.checkZoneRecordDistribution(1010, "example.com", 1010);
        assertThat(response.getData().isDistributed(), is(false));
    }

    @Test
    public void testCheckZoneRecordDistributionFailedCheck() {
        server.stubFixtureAt("checkZoneRecordDistribution/error.http");
        assertThat(() -> client.zones.checkZoneRecordDistribution(1010, "example.com", 1010),
                thrownException(is(instanceOf(DnsimpleException.class))));
    }

    @Test
    public void testCheckZoneRecordDistributionWhenZoneNotFound() {
        server.stubFixtureAt("notfound-zone.http");
        assertThat(() -> client.zones.checkZoneRecordDistribution(1010, "example.com", 1010),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCheckZoneRecordDistributionWhenZoneRecordNotFound() {
        server.stubFixtureAt("notfound-record.http");
        assertThat(() -> client.zones.checkZoneRecordDistribution(1010, "example.com", 1010),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }
}

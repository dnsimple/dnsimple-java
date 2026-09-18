package com.dnsimple.endpoints;

import com.dnsimple.data.ZoneRecord;
import com.dnsimple.data.ZoneRecordBatchChange;
import com.dnsimple.exception.BadRequestException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.ZoneRecordBatchChangeOptions;
import com.dnsimple.request.ZoneRecordOptions;
import com.dnsimple.request.ZoneRecordUpdateOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.number;
import static com.dnsimple.tools.CustomMatchers.property;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZoneRecordsTest extends DnsimpleTestBase {
    @Test
    public void testListZoneRecordsSupportsPagination() {
        client.zones.listZoneRecords(1, "example.com", ListOptions.empty().page(1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records?page=1"));
    }

    @Test
    public void testListZoneRecordsSupportsExtraRequestOptions() {
        client.zones.listZoneRecords(1, "example.com", ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records?foo=bar"));
    }

    @Test
    public void testListZoneRecordsSupportsSorting() {
        client.zones.listZoneRecords(1, "example.com", ListOptions.empty().sortAsc("name"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records?sort=name%3Aasc"));
    }

    @Test
    public void testListZoneRecordsProducesDomainList() {
        server.stubFixtureAt("listZoneRecords/success.http");
        List<ZoneRecord> zoneRecords = client.zones.listZoneRecords(1, "example.com").getData();
        assertThat(zoneRecords, hasSize(5));
        assertThat(zoneRecords.get(0).getId(), is(1L));
    }

    @Test
    public void testListZoneRecordsExposesPaginationInfo() {
        server.stubFixtureAt("listZoneRecords/success.http");
        PaginatedResponse<ZoneRecord> response = client.zones.listZoneRecords(1, "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetZoneRecord() {
        server.stubFixtureAt("getZoneRecord/success.http");
        ZoneRecord record = client.zones.getZoneRecord(1, "example.com", 2).getData();
        assertThat(record.getId(), is(5L));
        assertThat(record.getZoneId(), is("example.com"));
        assertThat(record.getParentId(), is(nullValue()));
        assertThat(record.getName(), is(""));
        assertThat(record.getContent(), is("mxa.example.com"));
        assertThat(record.getTtl(), is(600));
        assertThat(record.getPriority(), is(10));
        assertThat(record.getType(), is("MX"));
        assertThat(record.isSystemRecord(), is(false));
        assertThat(record.getRegions(), contains("SV1", "IAD"));
        assertThat(record.getCreatedAt(), is(OffsetDateTime.of(2016, 10, 5, 9, 51, 35, 0, UTC)));
        assertThat(record.getUpdatedAt(), is(OffsetDateTime.of(2016, 10, 5, 9, 51, 35, 0, UTC)));
    }

    @Test
    public void testGetZoneRecordWhenRecordNotFound() {
        server.stubFixtureAt("notfound-record.http");
        assertThat(() -> client.zones.getZoneRecord(1, "example.com", 2),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateZoneRecordSendsCorrectRequest() {
        server.stubFixtureAt("createZoneRecord/created.http");
        var options = ZoneRecordOptions.of("www", "A", "1.2.3.4").ttl(3600).priority(42);
        client.zones.createZoneRecord(1010, "example.com", options);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/zones/example.com/records"));
        assertThat(server.getRecordedRequest().getHeaders(), Matchers.hasEntry("Content-Type", "application/json"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasEntry("name", "www"),
                hasEntry("type", "A"),
                hasEntry("content", "1.2.3.4"),
                hasEntry(is("ttl"), number(3600)),
                hasEntry(is("priority"), number(42)),
                not(hasKey("regions"))
        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateZoneRecordAtRegionSendsCorrectRequest() {
        server.stubFixtureAt("createZoneRecord/created.http");
        var options = ZoneRecordOptions.of("www", "A", "1.2.3.4").ttl(3600).priority(42).regions("SV1", "IAD");
        client.zones.createZoneRecord(1010, "example.com", options);
        var jsonPayload = server.getRecordedRequest().getJsonObjectPayload();
        assertThat(jsonPayload, hasKey("regions"));
        var regions = (List<String>) jsonPayload.get("regions");
        assertThat(regions, contains("SV1", "IAD"));
    }

    @Test
    public void testCreateZoneRecordProducesZoneRecord() {
        server.stubFixtureAt("createZoneRecord/created.http");
        var options = ZoneRecordOptions.of("www", "A", "1.2.3.4").ttl(3600).priority(42);
        SimpleResponse<ZoneRecord> response = client.zones.createZoneRecord(1, "example.com", options);
        assertThat(response.getData().getId(), is(1L));
    }

    @Test
    public void testUpdateZoneRecordSendsCorrectRequest() {
        server.stubFixtureAt("updateZoneRecord/success.http");
        var options = ZoneRecordUpdateOptions.empty().name("www");
        client.zones.updateZoneRecord(1, "example.com", 2, options).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PATCH));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records/2"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasEntry("name", "www"),
                not(hasKey("regions"))
        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateZoneRecordAtRegionSendsCorrectRequest() {
        server.stubFixtureAt("updateZoneRecord/success.http");
        var options = ZoneRecordUpdateOptions.empty().name("www").regions("SV1", "IAD");
        client.zones.updateZoneRecord(1, "example.com", 2, options).getData();
        var jsonPayload = server.getRecordedRequest().getJsonObjectPayload();
        assertThat(jsonPayload, hasKey("regions"));
        var regions = (List<String>) jsonPayload.get("regions");
        assertThat(regions, contains("SV1", "IAD"));
    }

    @Test
    public void testUpdateZoneRecordProducesZoneRecord() {
        server.stubFixtureAt("updateZoneRecord/success.http");
        var options = ZoneRecordUpdateOptions.empty().name("www");
        ZoneRecord record = client.zones.updateZoneRecord(1, "example.com", 2, options).getData();
        assertThat(record.getId(), is(5L));
    }

    @Test
    public void testDeleteZoneRecord() {
        server.stubFixtureAt("deleteZoneRecord/success.http");
        client.zones.deleteZoneRecord(1, "example.com", 2);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records/2"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBatchChangeZoneRecordsSendsCorrectRequest() {
        server.stubFixtureAt("batchChangeZoneRecords/success.http");
        var options = ZoneRecordBatchChangeOptions.empty()
                .create(ZoneRecordOptions.of("ab", "A", "3.2.3.4"))
                .create(ZoneRecordOptions.of("ab", "A", "4.2.3.4").ttl(3600).priority(10).regions("SV1", "IAD"))
                .update(67622534, ZoneRecordUpdateOptions.empty().content("3.2.3.40"))
                .delete(67622509);
        client.zones.batchChangeZoneRecords(1010, "example.com", options);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/zones/example.com/batch"));
        var payload = server.getRecordedRequest().getJsonObjectPayload();
        var creates = (List<Map<String, Object>>) payload.get("creates");
        assertThat(creates, hasSize(2));
        assertThat(creates.get(0), is(Map.of("name", "ab", "type", "A", "content", "3.2.3.4")));
        assertThat(creates.get(1).get("name"), is("ab"));
        assertThat(creates.get(1).get("type"), is("A"));
        assertThat(creates.get(1).get("content"), is("4.2.3.4"));
        assertThat(creates.get(1).get("ttl"), is(number(3600)));
        assertThat(creates.get(1).get("priority"), is(number(10)));
        assertThat(creates.get(1).get("regions"), is(List.of("SV1", "IAD")));
        var updates = (List<Map<String, Object>>) payload.get("updates");
        assertThat(updates, hasSize(1));
        assertThat(updates.get(0).keySet(), containsInAnyOrder("id", "content"));
        assertThat(updates.get(0).get("id"), is(number(67622534)));
        assertThat(updates.get(0).get("content"), is("3.2.3.40"));
        var deletes = (List<Map<String, Object>>) payload.get("deletes");
        assertThat(deletes, hasSize(1));
        assertThat(deletes.get(0).keySet(), contains("id"));
        assertThat(deletes.get(0).get("id"), is(number(67622509)));
    }

    @Test
    public void testBatchChangeZoneRecordsOmitsEmptyOperations() {
        server.stubFixtureAt("batchChangeZoneRecords/success.http");
        var options = ZoneRecordBatchChangeOptions.empty().delete(67622509);
        client.zones.batchChangeZoneRecords(1010, "example.com", options);
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasKey("deletes"),
                not(hasKey("creates")),
                not(hasKey("updates"))
        ));
    }

    @Test
    public void testBatchChangeZoneRecordsProducesBatchChange() {
        server.stubFixtureAt("batchChangeZoneRecords/success.http");
        ZoneRecordBatchChange batch = client.zones.batchChangeZoneRecords(1010, "example.com", ZoneRecordBatchChangeOptions.empty()).getData();

        assertThat(batch.getCreates(), hasSize(2));
        ZoneRecord created = batch.getCreates().get(0);
        assertThat(created.getId(), is(67623409L));
        assertThat(created.getZoneId(), is("example.com"));
        assertThat(created.getName(), is("ab"));
        assertThat(created.getContent(), is("3.2.3.4"));
        assertThat(created.getTtl(), is(3600));
        assertThat(created.getPriority(), is(nullValue()));
        assertThat(created.getType(), is("A"));
        assertThat(created.getRegions(), contains("global"));
        assertThat(created.isSystemRecord(), is(false));
        assertThat(created.getCreatedAt(), is(OffsetDateTime.of(2025, 9, 5, 5, 25, 0, 0, UTC)));
        assertThat(batch.getCreates().get(1).getId(), is(67623410L));

        assertThat(batch.getUpdates(), hasSize(2));
        ZoneRecord updated = batch.getUpdates().get(0);
        assertThat(updated.getId(), is(67622534L));
        assertThat(updated.getName(), is("update1-1757049890"));
        assertThat(updated.getContent(), is("3.2.3.40"));
        assertThat(updated.getUpdatedAt(), is(OffsetDateTime.of(2025, 9, 5, 5, 25, 0, 0, UTC)));
        assertThat(batch.getUpdates().get(1).getId(), is(67622537L));

        assertThat(batch.getDeletes(), hasSize(2));
        assertThat(batch.getDeletes().get(0).getId(), is(67622509L));
        assertThat(batch.getDeletes().get(1).getId(), is(67622527L));
    }

    @Test
    public void testBatchChangeZoneRecordsWhenCreateValidationFails() {
        server.stubFixtureAt("batchChangeZoneRecords/error_400_create_validation_failed.http");
        var options = ZoneRecordBatchChangeOptions.empty().create(ZoneRecordOptions.of("ab", "SPF", "v=spf1 -all"));
        assertThat(() -> client.zones.batchChangeZoneRecords(1010, "example.com", options), allOf(
                thrownException(is(instanceOf(BadRequestException.class))),
                thrownException(property(BadRequestException::getStatusCode, is(400))),
                thrownException(property((BadRequestException e) -> e.getBody().get("message"), is("Validation failed"))),
                thrownException(property((BadRequestException e) -> e.getAttributeErrors(), hasKey("creates")))
        ));
    }

    @Test
    public void testBatchChangeZoneRecordsWhenUpdateValidationFails() {
        server.stubFixtureAt("batchChangeZoneRecords/error_400_update_validation_failed.http");
        var options = ZoneRecordBatchChangeOptions.empty().update(99999999, ZoneRecordUpdateOptions.empty().content("1.2.3.4"));
        assertThat(() -> client.zones.batchChangeZoneRecords(1010, "example.com", options), allOf(
                thrownException(is(instanceOf(BadRequestException.class))),
                thrownException(property((BadRequestException e) -> e.getBody().get("message"), is("Validation failed"))),
                thrownException(property((BadRequestException e) -> e.getAttributeErrors(), hasKey("updates")))
        ));
    }

    @Test
    public void testBatchChangeZoneRecordsWhenDeleteValidationFails() {
        server.stubFixtureAt("batchChangeZoneRecords/error_400_delete_validation_failed.http");
        var options = ZoneRecordBatchChangeOptions.empty().delete(67622509);
        assertThat(() -> client.zones.batchChangeZoneRecords(1010, "example.com", options), allOf(
                thrownException(is(instanceOf(BadRequestException.class))),
                thrownException(property((BadRequestException e) -> e.getBody().get("message"), is("Validation failed"))),
                thrownException(property((BadRequestException e) -> e.getAttributeErrors(), hasKey("deletes")))
        ));
    }

    @Test
    public void testBatchChangeZoneRecordsWhenZoneNotFound() {
        server.stubFixtureAt("notfound-zone.http");
        assertThat(() -> client.zones.batchChangeZoneRecords(1010, "example.com", ZoneRecordBatchChangeOptions.empty().delete(1)),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }
}

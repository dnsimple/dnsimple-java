package com.dnsimple.endpoints;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.DSRecordOptions;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static com.dnsimple.http.HttpMethod.DELETE;
import static com.dnsimple.http.HttpMethod.GET;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainDelegationSignerRecordsTest extends DnsimpleTestBase {
    @Test
    public void testListDelegationSignerRecordsSupportsPagination() {
        client.domains.listDelegationSignerRecords(1, "1010", ListOptions.empty().page(1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/1010/ds_records?page=1"));
    }

    @Test
    public void testListDelegationSignerRecordsSupportsExtraRequestOptions() {
        client.domains.listDelegationSignerRecords(1, "1010", ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/1010/ds_records?foo=bar"));
    }

    @Test
    public void testListDelegationSignerRecordsSupportsSorting() {
        client.domains.listDelegationSignerRecords(1, "1010", ListOptions.empty().sortAsc("created_at"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/1010/ds_records?sort=created_at%3Aasc"));
    }

    @Test
    public void testListDelegationSignerRecordsProducesDelegationSignerRecordList() {
        server.stubFixtureAt("listDelegationSignerRecords/success.http");
        List<DelegationSignerRecord> dsRecords = client.domains.listDelegationSignerRecords(1, "1010").getData();
        assertThat(dsRecords, hasSize(1));
        assertThat(dsRecords.get(0).getId(), is(24L));
    }

    @Test
    public void testListDelegationSignerRecordsExposesPaginationInfo() {
        server.stubFixtureAt("listDelegationSignerRecords/success.http");
        PaginatedResponse<DelegationSignerRecord> response = client.domains.listDelegationSignerRecords(1, "1010");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetDelegationSignerRecord() {
        server.stubFixtureAt("getDelegationSignerRecord/success.http");
        DelegationSignerRecord dsRecord = client.domains.getDelegationSignerRecord(1, "example.com", 24).getData();
        assertThat(dsRecord.getId(), is(24L));
        assertThat(dsRecord.getDomainId(), is(1010L));
        assertThat(dsRecord.getAlgorithm(), is("8"));
        assertThat(dsRecord.getDigest(), is("C1F6E04A5A61FBF65BF9DC8294C363CF11C89E802D926BDAB79C55D27BEFA94F"));
        assertThat(dsRecord.getDigestType(), is("2"));
        assertThat(dsRecord.getKeytag(), is("44620"));
        assertThat(dsRecord.getCreatedAt(), is(OffsetDateTime.of(2017, 3, 3, 13, 49, 58, 0, UTC)));
        assertThat(dsRecord.getUpdatedAt(), is(OffsetDateTime.of(2017, 3, 3, 13, 49, 58, 0, UTC)));
    }

    @Test
    public void testGetDelegationSignerRecordWhenNotFound() {
        server.stubFixtureAt("notfound-delegationSignerRecord.http");
        assertThat(() -> client.domains.getDelegationSignerRecord(1, "example.com", 0),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateDelegationSignerRecordProducesDelegationSignerRecord() {
        server.stubFixtureAt("createDelegationSignerRecord/created.http");
        DSRecordOptions attributes = DSRecordOptions.of("13", "684a1f049d7d082b7f98691657da5a65764913df7f065f6f8c36edf62d66ca03", "2", "2371");
        SimpleResponse<DelegationSignerRecord> response = client.domains.createDelegationSignerRecord(1, "example.com", attributes);
        assertThat(response.getData().getId(), is(2L));
    }

    @Test
    public void testDeleteDelegationSignerRecord() {
        server.stubFixtureAt("deleteDelegationSignerRecord/success.http");
        client.domains.deleteDelegationSignerRecord(1, "example.com", 24);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/ds_records/24"));
    }
}

package com.dnsimple.regression;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.dnsimple.Client;
import com.dnsimple.DnsimpleTestBase;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.DeleteZoneRecordResponse;
import java.io.IOException;
import org.junit.Test;

public class DeleteZoneRecordTest extends DnsimpleTestBase {

  /**
   * This test ensures that our Java client can deal with HTTP 204 No Content
   * responses without throwing an IllegalArgumentError after trying to parse
   * an empty response as a JSON document
   */
  @Test
  public void issue_13_deleteZoneRecord_throws_IllegalArgumentException() throws IOException, DnsimpleException {
    server.stubFixtureAt("deleteZoneRecord/success.http");
    server.expectDelete("/v2/1/zones/example.com/records/2");
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";
    String recordId = "2";

    DeleteZoneRecordResponse response = client.zones.deleteZoneRecord(accountId, zoneId, recordId);
    assertThat(response.getData(), nullValue());
  }
}

package com.dnsimple.regression;

import static org.junit.Assert.assertEquals;

import com.dnsimple.Client;
import com.dnsimple.Dnsimple;
import com.dnsimple.DnsimpleTestBase;
import com.dnsimple.TestHttpServer;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.DeleteZoneRecordResponse;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeleteZoneRecordTest extends DnsimpleTestBase {
  private TestHttpServer server;
  private String backupApiBase;

  @Before
  public void setUp() {
    server = new TestHttpServer(12345);
    server.start();
    backupApiBase = Dnsimple.getApiBase();
    Dnsimple.setApiBase(server.getBaseURL());
  }

  @After
  public void tearDown() {
    server.stop();
    Dnsimple.setApiBase(backupApiBase);
  }

  /**
   * This test ensures that our Java client can deal with HTTP 204 No Content
   * responses without throwing an IllegalArgumentError after trying to parse
   * an empty response as a JSON document
   */
  @Test
  public void issue_13_deleteZoneRecord_throws_IllegalArgumentException() throws IOException, DnsimpleException {
    server.stubFixture(resourcePath("deleteZoneRecord/success.http"));
    Client client = new Client();

    String accountId = "1";
    String zoneId = "example.com";
    String recordId = "2";

    DeleteZoneRecordResponse response = client.zones.deleteZoneRecord(accountId, zoneId, recordId);
    assertEquals(null, response.getData());
  }
}

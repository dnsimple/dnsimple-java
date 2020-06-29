package com.dnsimple;

import cocotero.GoogleHttpEndpointClient;
import com.dnsimple.tools.TestHttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * A base class that DNSimple tests can inherit from to provide set everything
 * required to test expectations agains HTTP fixture files.
 */
public abstract class DnsimpleTestBase {
  protected static final String TEST_ACCESS_TOKEN = "test-access-token";
  protected static final String TEST_USER_AGENT = "test-user-agent";
  protected static TestHttpServer server;
  private static String backupApiBase;
  protected Client client;

  @BeforeClass
  public static void init() {
    server = new TestHttpServer(12345);
    server.start();
    backupApiBase = Dnsimple.getApiBase();
    Dnsimple.setApiBase(server.getBaseURL());
  }

  @AfterClass
  public static void tearDown() {
    server.stop();
    Dnsimple.setApiBase(backupApiBase);
  }

  @Before
  public void setUp() {
    server.reset();
    GoogleHttpEndpointClient ec = new GoogleHttpEndpointClient();
    ec.setAccessToken(TEST_ACCESS_TOKEN);
    ec.setUserAgent(TEST_USER_AGENT);
    client = new Client(ec);
  }
}

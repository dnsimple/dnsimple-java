package com.dnsimple;

import com.dnsimple.tools.TestHttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * A base class that DNSimple tests can inherit from to provide set everything
 * required to test expectations agains HTTP fixture files.
 */
public abstract class DnsimpleTestBase {
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
    client = new Client();
  }
}

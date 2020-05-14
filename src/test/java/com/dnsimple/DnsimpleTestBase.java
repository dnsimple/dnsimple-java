package com.dnsimple;

import com.dnsimple.tools.TestHttpServer;
import org.junit.After;
import org.junit.Before;

/**
 * A base class that DNSimple tests can inherit from to provide set everything
 * required to test expectations agains HTTP fixture files.
 */
public abstract class DnsimpleTestBase {
  protected TestHttpServer server;
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
}

package com.dnsimple;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DnsimpleTest {
  @Test
  public void testApiBase() {
    String backupApiBase = Dnsimple.getApiBase();
    Dnsimple.setApiBase("something");
    assertEquals("something", Dnsimple.getApiBase());
    Dnsimple.setApiBase(backupApiBase);
  }
}

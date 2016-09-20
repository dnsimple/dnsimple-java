package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DnsimpleTest {
  @Test
  public void testApiBase() throws DnsimpleException {
    assertEquals("https://api.dnsimple.com", Dnsimple.getApiBase());
  }
}

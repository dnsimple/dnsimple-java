package com.dnsimple;

public abstract class Dnsimple {
  public static final String PRODUCTION_API_BASE = "https://api.dnsimple.com";

  private static volatile String apiBase = PRODUCTION_API_BASE;

  public static String getApiBase() {
    return apiBase;
  }
}

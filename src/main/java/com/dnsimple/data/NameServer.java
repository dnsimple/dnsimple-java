package com.dnsimple.data;

public class NameServer {

  private Integer id;


  private String name;


  private String ipv4;


  private String ipv6;


  private String createdAt;


  private String updatedAt;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIpv4() {
        return ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

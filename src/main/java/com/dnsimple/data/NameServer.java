package com.dnsimple.data;

public class NameServer {
    private final Integer id;
    private final String name;
    private final String ipv4;
    private final String ipv6;
    private final String createdAt;
    private final String updatedAt;

    public NameServer(Integer id, String name, String ipv4, String ipv6, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

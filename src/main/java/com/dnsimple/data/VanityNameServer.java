package com.dnsimple.data;

import java.time.OffsetDateTime;

public class VanityNameServer {
    private final Long id;
    private final String name;
    private final String ipv4;
    private final String ipv6;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public VanityNameServer(Long id, String name, String ipv4, String ipv6, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

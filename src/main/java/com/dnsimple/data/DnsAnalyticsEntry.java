package com.dnsimple.data;

import java.time.LocalDate;

public class DnsAnalyticsEntry {
    private final String zoneName;
    private final LocalDate date;
    private final Long volume;

    public DnsAnalyticsEntry(String zoneName, LocalDate date, Long volume) {
        this.zoneName = zoneName;
        this.date = date;
        this.volume = volume;
    }

    /**
     * @return The zone name, or null when the query does not group by zone name
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * @return The date, or null when the query does not group by date
     */
    public LocalDate getDate() {
        return date;
    }

    public Long getVolume() {
        return volume;
    }
}

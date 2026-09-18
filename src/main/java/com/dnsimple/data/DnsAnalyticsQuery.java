package com.dnsimple.data;

import java.time.LocalDate;

public class DnsAnalyticsQuery {
    private final Long accountId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String sort;
    private final Integer page;
    private final Integer perPage;
    private final String groupings;

    public DnsAnalyticsQuery(Long accountId, LocalDate startDate, LocalDate endDate, String sort, Integer page, Integer perPage, String groupings) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sort = sort;
        this.page = page;
        this.perPage = perPage;
        this.groupings = groupings;
    }

    public Long getAccountId() {
        return accountId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getSort() {
        return sort;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public String getGroupings() {
        return groupings;
    }
}

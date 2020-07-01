package com.dnsimple.data;

public class Pagination {
    private final Integer currentPage;
    private final Integer perPage;
    private final Integer totalEntries;
    private final Integer totalPages;

    public Pagination(Integer currentPage, Integer perPage, Integer totalEntries, Integer totalPages) {
        this.currentPage = currentPage;
        this.perPage = perPage;
        this.totalEntries = totalEntries;
        this.totalPages = totalPages;
    }

    public static Pagination empty() {
        return new Pagination(0, 0, 0, 0);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getTotalEntries() {
        return totalEntries;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}

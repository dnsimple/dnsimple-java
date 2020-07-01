package com.dnsimple.data;

public class Pagination {

  private Integer currentPage;


  private Integer perPage;


  private Integer totalEntries;


  private Integer totalPages;

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

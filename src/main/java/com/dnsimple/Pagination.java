package com.dnsimple;

import com.google.api.client.util.Key;

public class Pagination {
  @Key("current_page")
  private Integer currentPage;

  @Key("per_page")
  private Integer perPage;

  @Key("total_entries")
  private Integer totalEntries;

  @Key("total_pages")
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

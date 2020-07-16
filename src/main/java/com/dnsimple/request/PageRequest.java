package com.dnsimple.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PageRequest {
    static final int MAX_ITEMS_PER_PAGE = 100;
    final int page;
    final Optional<Integer> itemsPerPage;

    PageRequest(int page, Optional<Integer> itemsPerPage) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
    }

    public List<String> asQueryStringParams() {
        List<String> qsParams = new ArrayList<>();
        qsParams.add(String.format("page=%d", page));
        itemsPerPage.ifPresent(ipp -> qsParams.add(String.format("per_page=%d", ipp)));
        return qsParams;
    }
}

package com.dnsimple.request;

import java.util.ArrayList;
import java.util.List;

public class PageRequest {
    static final int MAX_ITEMS_PER_PAGE = 100;
    final int page;
    final Integer itemsPerPage;

    PageRequest(int page, Integer itemsPerPage) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
    }

    public List<String> asQueryStringParams() {
        List<String> qsParams = new ArrayList<>();
        qsParams.add(String.format("page=%d", page));
        if (itemsPerPage != null)
            qsParams.add(String.format("per_page=%d", itemsPerPage));
        return qsParams;
    }
}

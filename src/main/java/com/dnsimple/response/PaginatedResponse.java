package com.dnsimple.response;

import java.util.List;

import static java.util.Collections.emptyList;

public class PaginatedResponse<T> implements ApiResponse {
    private final List<T> data;
    private final Pagination pagination;

    public PaginatedResponse(List<T> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public static <U> PaginatedResponse<U> empty() {
        return new PaginatedResponse<>(emptyList(), Pagination.empty());
    }

    public List<T> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

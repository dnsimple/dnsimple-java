package com.dnsimple.response;

import java.util.List;

public class PaginatedResponseWithQuery<T, Q> extends PaginatedResponse<T> {
    private final Q query;

    public PaginatedResponseWithQuery(List<T> data, Pagination pagination, Q query) {
        super(data, pagination);
        this.query = query;
    }

    public Q getQuery() {
        return query;
    }
}

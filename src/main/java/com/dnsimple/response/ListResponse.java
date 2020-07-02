package com.dnsimple.response;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListResponse<T> extends ApiResponse {
    private final List<T> data;

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public static <U> ListResponse<U> empty() {
        return new ListResponse<>(emptyList());
    }

    public static <W> ListResponse<W> from(List<W> data) {
        return new ListResponse<>(data);
    }

    public List<T> getData() {
        return data;
    }
}

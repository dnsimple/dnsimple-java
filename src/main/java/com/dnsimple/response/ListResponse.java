package com.dnsimple.response;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListResponse<T> {
    private final List<T> data;

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public static <U> ListResponse<U> empty() {
        return new ListResponse<>(emptyList());
    }

    public List<T> getData() {
        return data;
    }
}

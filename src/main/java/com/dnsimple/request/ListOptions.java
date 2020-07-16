package com.dnsimple.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dnsimple.request.PageRequest.MAX_ITEMS_PER_PAGE;
import static com.dnsimple.request.SortField.Order.ASC;
import static com.dnsimple.request.SortField.Order.DESC;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ListOptions {
    private final Optional<PageRequest> pageRequest;
    private final List<Filter> filters;
    private final List<SortField> sortFields;

    private ListOptions(Optional<PageRequest> pageRequest, List<Filter> filters, List<SortField> sortFields) {
        this.pageRequest = pageRequest;
        this.filters = filters;
        this.sortFields = sortFields;
    }

    public static ListOptions empty() {
        return new ListOptions(Optional.empty(), new ArrayList<>(), new ArrayList<>());
    }

    public String asQueryString() {
        List<String> qsParams = new ArrayList<>();
        qsParams.addAll(pageRequest.map(PageRequest::asQueryStringParams).orElse(emptyList()));
        qsParams.addAll(filters.stream().map(Filter::asQueryStringParam).collect(toList()));
        if (!sortFields.isEmpty())
            qsParams.add("sort=" + sortFields.stream().map(SortField::asQueryStringParam).collect(joining(",")));
        return qsParams.isEmpty() ? "" : "?" + String.join("&", qsParams);
    }

    public static class Builder {
        private PageRequest pageRequest;
        private final List<Filter> filters = new ArrayList<>();
        private final List<SortField> sortFields = new ArrayList<>();

        public ListOptions.Builder page(int page) {
            pageRequest = new PageRequest(page, Optional.empty());
            return this;
        }

        public ListOptions.Builder page(int page, int itemsPerPage) {
            if (itemsPerPage > MAX_ITEMS_PER_PAGE)
                throw new IllegalArgumentException("The requested items per page can't be greater than " + MAX_ITEMS_PER_PAGE);
            pageRequest = new PageRequest(page, Optional.of(itemsPerPage));
            return this;
        }

        public ListOptions.Builder filter(String name, String value) {
            filters.add(new Filter(name, value));
            return this;
        }

        public ListOptions.Builder sortAsc(String name) {
            sortFields.add(new SortField(name, ASC));
            return this;
        }

        public ListOptions.Builder sortDesc(String name) {
            sortFields.add(new SortField(name, DESC));
            return this;
        }

        public ListOptions build() {
            return new ListOptions(Optional.ofNullable(pageRequest), filters, sortFields);
        }
    }
}

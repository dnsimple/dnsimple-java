package com.dnsimple.request;

import java.net.URLEncoder;
import java.util.*;

import static com.dnsimple.request.PageRequest.MAX_ITEMS_PER_PAGE;
import static com.dnsimple.request.SortField.Order.ASC;
import static com.dnsimple.request.SortField.Order.DESC;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ListOptions {
    private final PageRequest pageRequest;
    private final List<Filter> filters;
    private final List<SortField> sortFields;
    private final Map<String, String> otherOptions = new HashMap<>();

    private ListOptions(PageRequest pageRequest, List<Filter> filters, List<SortField> sortFields) {
        this.pageRequest = pageRequest;
        this.filters = filters;
        this.sortFields = sortFields;
    }

    public static ListOptions empty() {
        return new ListOptions(null, new ArrayList<>(), new ArrayList<>());
    }

    public ListOptions page(int page) {
        return new ListOptions(new PageRequest(page, null), filters, sortFields);
    }

    public ListOptions page(int page, int itemsPerPage) {
        if (itemsPerPage > MAX_ITEMS_PER_PAGE)
            throw new IllegalArgumentException("The requested items per page can't be greater than " + MAX_ITEMS_PER_PAGE);
        return new ListOptions(new PageRequest(page, itemsPerPage), filters, sortFields);
    }

    public ListOptions filter(String name, String value) {
        List<Filter> newFilters = new ArrayList<>(filters);
        newFilters.add(new Filter(name, value));
        return new ListOptions(pageRequest, newFilters, sortFields);
    }

    public ListOptions sortAsc(String name) {
        List<SortField> newSortFields = new ArrayList<>(sortFields);
        newSortFields.add(new SortField(name, ASC));
        return new ListOptions(pageRequest, filters, newSortFields);
    }

    public ListOptions sortDesc(String name) {
        List<SortField> newSortFields = new ArrayList<>(sortFields);
        newSortFields.add(new SortField(name, DESC));
        return new ListOptions(pageRequest, filters, newSortFields);
    }

    public ListOptions setOtherOption(String name, String value) {
        this.otherOptions.put(name, value);
        return this;
    }

    public String asQueryString() {
        List<String> qsParams = new ArrayList<>();
        if (pageRequest != null)
            qsParams.addAll(pageRequest.asQueryStringParams());
        qsParams.addAll(filters.stream().map(Filter::asQueryStringParam).collect(toList()));
        if (!sortFields.isEmpty())
            qsParams.add("sort=" + sortFields.stream().map(SortField::asQueryStringParam).collect(joining(",")));
        for (var e : this.otherOptions.entrySet()) {
            qsParams.add(String.format(
                    "%s=%s",
                    URLEncoder.encode(e.getKey(), UTF_8),
                    URLEncoder.encode(e.getValue(), UTF_8)
            ));
        }
        return qsParams.isEmpty() ? "" : "?" + String.join("&", qsParams);
    }
}

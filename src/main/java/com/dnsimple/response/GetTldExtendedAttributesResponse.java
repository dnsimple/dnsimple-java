package com.dnsimple.response;

import com.dnsimple.data.TldExtendedAttribute;

import java.util.List;

import static java.util.Collections.emptyList;

public class GetTldExtendedAttributesResponse extends ApiResponse {
    private final List<TldExtendedAttribute> data;

    public GetTldExtendedAttributesResponse() {
        data = emptyList();
    }

    public GetTldExtendedAttributesResponse(List<TldExtendedAttribute> data) {
        this.data = data;
    }

    public List<TldExtendedAttribute> getData() {
        return data;
    }
}

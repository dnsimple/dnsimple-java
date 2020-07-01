package com.dnsimple.response;

import com.dnsimple.data.TldExtendedAttribute;

import java.util.List;

public class GetTldExtendedAttributesResponse extends ApiResponse {
    private List<TldExtendedAttribute> data;

    public List<TldExtendedAttribute> getData() {
        return data;
    }
}

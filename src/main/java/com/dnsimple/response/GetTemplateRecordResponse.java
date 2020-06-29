package com.dnsimple.response;

import com.dnsimple.data.TemplateRecord;

public class GetTemplateRecordResponse extends ApiResponse {
    private final TemplateRecord data;

    public GetTemplateRecordResponse() {
        data = null;
    }

    public GetTemplateRecordResponse(TemplateRecord data) {
        this.data = data;
    }

    public TemplateRecord getData() {
        return data;
    }
}

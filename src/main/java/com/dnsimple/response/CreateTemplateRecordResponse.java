package com.dnsimple.response;

import com.dnsimple.data.TemplateRecord;

public class CreateTemplateRecordResponse extends ApiResponse {
    private final TemplateRecord data;

    public CreateTemplateRecordResponse() {
        data = null;
    }

    public CreateTemplateRecordResponse(TemplateRecord data) {
        this.data = data;
    }

    public TemplateRecord getData() {
        return data;
    }
}

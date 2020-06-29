package com.dnsimple.response;

import com.dnsimple.data.Template;

public class GetTemplateResponse extends ApiResponse {
    private final Template data;

    public GetTemplateResponse() {
        data = null;
    }

    public GetTemplateResponse(Template data) {
        this.data = data;
    }

    public Template getData() {
        return data;
    }
}

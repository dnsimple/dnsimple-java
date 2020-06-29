package com.dnsimple.response;

import com.dnsimple.data.Template;

public class UpdateTemplateResponse extends ApiResponse {
    private final Template data;

    public UpdateTemplateResponse() {
        data = null;
    }

    public UpdateTemplateResponse(Template data) {
        this.data = data;
    }

    public Template getData() {
        return data;
    }
}

package com.dnsimple.response;

import com.dnsimple.data.Template;

public class CreateTemplateResponse extends ApiResponse {
    private final Template data;

    public CreateTemplateResponse() {
        data = null;
    }

    public CreateTemplateResponse(Template data) {
        this.data = data;
    }

    public Template getData() {
        return data;
    }
}

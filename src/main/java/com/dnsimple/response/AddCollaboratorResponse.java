package com.dnsimple.response;

import com.dnsimple.data.Collaborator;

public class AddCollaboratorResponse extends ApiResponse {
    private final Collaborator data;

    public AddCollaboratorResponse() {
        data = null;
    }

    public AddCollaboratorResponse(Collaborator data) {
        this.data = data;
    }

    public Collaborator getData() {
        return data;
    }
}


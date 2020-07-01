package com.dnsimple.response;

import com.dnsimple.data.Collaborator;

public class AddCollaboratorResponse extends ApiResponse {
    private Collaborator data;

    public Collaborator getData() {
        return data;
    }
}


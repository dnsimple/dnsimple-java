package com.dnsimple.response;

import com.dnsimple.data.Collaborator;

import com.google.api.client.util.Key;

public class AddCollaboratorResponse extends ApiResponse {
  @Key("data")
  private Collaborator data;

  public Collaborator getData() {
    return data;
  }
}


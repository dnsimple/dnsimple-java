package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;

public class Identity {
  private Client client;

  public Identity(Client client) {
    this.client = client;
  }

  public WhoamiResponse whoami() throws DnsimpleException, IOException {
    HttpResponse response = client.get("whoami");

    WhoamiResponse res = null;
    InputStream in = response.getContent();
    try {
      JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
      res = jsonParser.parse(WhoamiResponse.class);
    } finally {
      in.close();
    }

    return res;
  }
}

package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;

public class Domains {
  private Client client;

  public Domains(Client client) {
    this.client = client;
  }

  public ListDomainsResponse listDomains(String accountId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains");

    ListDomainsResponse res = null;
    InputStream in = response.getContent();
    try {
      JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
      res = jsonParser.parse(ListDomainsResponse.class);
    } finally {
      in.close();
    }

    return res;
  }
}

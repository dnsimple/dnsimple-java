package com.dnsimple;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;

public abstract class DnsimpleTestBase {

  public Client mockClient(final String httpResponse) {
    Client client = new Client();

    HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
            return mockResponse(response, httpResponse);
          }
        };
      }
    };

    client.setTransport(transport);

    return client;
  }

  public String resource(String path) throws IOException {
    InputStream resource = getClass().getResourceAsStream(path);

    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    byte[] buf = new byte [1024];

    for( int i = resource.read(buf); i > 0; i = resource.read(buf)) {
      out.write(buf,0,i);
    }

    return out.toString("utf8");

  }

  private MockLowLevelHttpResponse mockResponse(MockLowLevelHttpResponse response, String httpResponse) throws IOException {
    final Map<String,String> headers = new HashMap<String,String>();
    final ArrayList<String> data = new ArrayList<String>();
    String[] lines = httpResponse.split("\\r?\\n");

    String[] statusLineParts = lines[0].split("\\s+");
    String httpVersion = statusLineParts[0];
    String statusCode = statusLineParts[1];
    String reasonPhrase = statusLineParts[2];

    response.setStatusCode(Integer.parseInt(statusCode));

    for (int i = 1; i < lines.length; i++) {

      if (lines[i].trim().length() == 0) {
        // read until the end of the file for the data
        for (int j = i; j < lines.length; j++) {
          data.add(lines[j]);
        }

        response.setContent(String.join("\n", data));

        return response;
      } else {
        String[] kv = lines[i].split(": ");
        response.addHeader(kv[0], kv[1]);
      }
    }

    return response;
  }
}

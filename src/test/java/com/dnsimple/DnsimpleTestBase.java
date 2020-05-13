package com.dnsimple;

import static org.junit.Assert.assertEquals;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A base class that DNSimple tests can inherit from to provide HTTP mocking and expectations.
 */
public abstract class DnsimpleTestBase {

  public static final String TEST_ACCESS_TOKEN = "test-access-token";

  /**
   * Return a Client that is mocked to return the given HTTP response.
   *
   * @param httpResponse The full HTTP response data
   * @return The Client instance
   */
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

  /**
   * Return a Client that is configured to expect a specific URL.
   *
   * @param expectedUrl The URL string that is expected
   * @return The Client instance
   */
  public Client expectClient(final String expectedUrl) {
    return expectClient(expectedUrl, HttpMethods.GET);
  }

  /**
   * Return a Client that is configured to expect a specific URL and HTTP method.
   *
   * @param expectedUrl    The URL string that is expected
   * @param expectedMethod The HTTP method String that is expected
   * @return The Client instance
   */
  public Client expectClient(final String expectedUrl, final String expectedMethod) {
    return expectClient(expectedUrl, expectedMethod, new HashMap<String, Object>(), new Object());
  }

  /**
   * Return a Client that is configured to expect a specific URL, HTTP method, request attributes, and HTTP headers.
   *
   * @param expectedUrl        The URL string that is expected
   * @param expectedMethod     The HTTP method String that is expected
   * @param expectedHeaders    a Map<String, Object> of headers
   * @param expectedAttributes A map of values as attributes
   * @return The Client instance
   */
  public Client expectClient(final String expectedUrl, final String expectedMethod, final Map<String, Object> expectedHeaders, final Object expectedAttributes) {
    Client client = new Client();

    HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        assertEquals(new GenericUrl(expectedUrl), new GenericUrl(url));
        assertEquals(expectedMethod, method);

        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            if (!getContentAsString().equals("")) {
              JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(getContentAsString());
              Map<String, Object> attributes = jsonParser.parse(GenericJson.class);
              assertEquals(expectedAttributes, attributes);
            }

            for (Map.Entry<String, Object> expectedHeader : expectedHeaders.entrySet()) {
              assertEquals(expectedHeader.getValue(), getHeaders().get(expectedHeader.getKey()));
            }

            MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
            return mockResponse(response, resource("pages-1of3.http"));
          }
        };
      }
    };

    client.setTransport(transport);

    return client;
  }

  /**
   * Returns a Client that is configured to expect certain request values and return
   * the given HTTP response.
   *
   * @param expectedUrl    The URL string that is expected
   * @param expectedMethod The HTTP method String that is expected
   * @param httpResponse   The full HTTP response data
   * @return The Client instance
   */
  public Client mockAndExpectClient(final String expectedUrl, final String expectedMethod, final String httpResponse) {
    return mockAndExpectClient(expectedUrl, expectedMethod, new HashMap<String, Object>(), new Object(), httpResponse);
  }

  /**
   * Returns a Client that is configured to expect certain request values and return
   * the given HTTP response.
   *
   * @param expectedUrl        The URL string that is expected
   * @param expectedMethod     The HTTP method String that is expected
   * @param expectedHeaders    A Map<String, Object> of expected headers
   * @param expectedAttributes A map of values as attributes
   * @param httpResponse       The full HTTP response data
   * @return The Client instance
   */
  public Client mockAndExpectClient(final String expectedUrl, final String expectedMethod, final Map<String, Object> expectedHeaders, final Object expectedAttributes, final String httpResponse) {
    Client client = new Client();
    client.setAccessToken(TEST_ACCESS_TOKEN);

    HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        assertEquals(new GenericUrl(expectedUrl), new GenericUrl(url));
        assertEquals(expectedMethod, method);

        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            if (!getContentAsString().equals("")) {
              JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(getContentAsString());
              if (expectedAttributes instanceof List) {
                Object attributes = jsonParser.parse(List.class);
                assertEquals(expectedAttributes, attributes);
              } else {
                Object attributes = jsonParser.parse(GenericJson.class);
                assertEquals(expectedAttributes, attributes);
              }
            }

            for (Map.Entry<String, Object> expectedHeader : expectedHeaders.entrySet()) {
              assertEquals(expectedHeader.getValue(), getHeaders().get(expectedHeader.getKey()));
            }

            MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
            return mockResponse(response, httpResponse);
          }
        };
      }
    };

    client.setTransport(transport);

    return client;
  }

  /**
   * Load a resource for the given path relative to the `src/test/resources` directory.
   *
   * @param path The path where the resource should be
   * @return The resource data
   * @throws IOException Any IO exception
   */
  public String resource(String path) throws IOException {
    InputStream resource = getClass().getResourceAsStream(path);
    if (resource == null) {
      throw new FileNotFoundException("Resource " + path + " not found");
    }

    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    byte[] buf = new byte[1024];

    for (int i = resource.read(buf); i > 0; i = resource.read(buf)) {
      out.write(buf, 0, i);
    }

    return out.toString("utf8");
  }

  /**
   * Returns the java.nio.Path for the given resource file location
   * relative to the `src/test/resources` directory.
   *
   * @param resourceFile The path where the resource should be
   * @return The Path to the provided file
   */
  public Path resourcePath(String resourceFile) {
    try {
      return Paths.get(DnsimpleTestBase.class.getResource(resourceFile.startsWith("/") ? resourceFile.substring(1) : resourceFile).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Get the default HttpHeaders that should be expected on every request.
   * <p>
   * You may set additional headers in the collection as necessary.
   *
   * @return The default HttpHeaders
   */
  public HttpHeaders getDefaultHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept("application/json");
    headers.setUserAgent("dnsimple-java/" + Dnsimple.VERSION + " Google-HTTP-Java-Client/1.35.0 (gzip)");
    return headers;
  }

  private MockLowLevelHttpResponse mockResponse(MockLowLevelHttpResponse response, String httpResponse) throws IOException {
    final Map<String, String> headers = new HashMap<String, String>();
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

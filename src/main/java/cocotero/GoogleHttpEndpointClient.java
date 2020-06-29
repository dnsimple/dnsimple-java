package cocotero;

import com.dnsimple.Dnsimple;
import com.dnsimple.endpoints.http.HttpEndpointClient;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.ApiResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.v2.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;
import io.mikael.urlbuilder.UrlBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class GoogleHttpEndpointClient implements HttpEndpointClient {

  public static final String DEFAULT_USER_AGENT = "dnsimple-java/" + Dnsimple.VERSION;

  private static final String API_VERSION_PATH = "/v2/";
  private static final String MIME_APPLICATION_JSON = "application/json";

  private HttpTransport transport;
  private String accessToken;
  private String userAgent;

  public GoogleHttpEndpointClient() {
    this.transport = new ApacheHttpTransport();
  }

  /**
   * Set the underlying transport mechanism.
   * <p>
   * This method is primarily used to specify a mocked transport layer during testing.
   *
   * @param transport The transport instance
   */
  public void setTransport(HttpTransport transport) {
    this.transport = transport;
  }

  /**
   * Set the access token to use for the client instance.
   *
   * @param accessToken The access token string
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  /**
   * Set the user agent to use for the client instance.
   *
   * @param userAgent The user agent string
   */
  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }


  @Override
  public Object get(String path, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException {
    HttpResponse response = request(HttpMethods.GET, versionedPath(path), null, options);
    return parseResponse(response, c);
  }

  @Override
  public Object post(String path, Map<String, Object> attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException {
    return parseResponse(request(HttpMethods.POST, versionedPath(path), attributes, null), c);
  }

  @Override
  public Object put(String path, Object attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException {
    return parseResponse(request(HttpMethods.PUT, versionedPath(path), attributes, null), c);
  }

  @Override
  public Object patch(String path, Object attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException {
    return parseResponse(request(HttpMethods.PATCH, versionedPath(path), attributes, null), c);
  }

  @Override
  public Object delete(String path, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException {
    return parseResponse(request(HttpMethods.DELETE, versionedPath(path), null, null), c);
  }

  private HttpResponse request(String method, String url, Object data, Map<String, Object> options) throws DnsimpleException, IOException {
    HttpContent content = null;
    if (data != null) {
      content = new JsonHttpContent(new GsonFactory(), data);
    }

    HttpRequest request = transport.createRequestFactory().buildRequest(method, buildUrl(url, options), content);

    HttpHeaders headers = request.getHeaders();
    headers.setAccept(MIME_APPLICATION_JSON);

    // Add the user agent string to the headers
    ArrayList<String> fullUserAgent = new ArrayList<String>();
    if (userAgent != null) {
      fullUserAgent.add(userAgent);
    }
    fullUserAgent.add(DEFAULT_USER_AGENT);
    headers.setUserAgent(String.join(" ", fullUserAgent));

    // Add the authorization to the headers
    headers.setAuthorization("Bearer " + accessToken);

    if (data != null) {
      headers.setContentType(MIME_APPLICATION_JSON);
    }

    try {
      return request.execute();
    } catch (HttpResponseException e) {
      throw DnsimpleException.transformException(e);
    }
  }

  /**
   * Parse the response from the HTTP call into an instance of the given class.
   *
   * @param response The parsed response object
   * @param c        The class to instantiate and use to build the response object
   * @return The ApiResponse object
   * @throws IOException Any IO errors
   */
  private ApiResponse parseResponse(HttpResponse response, Class<?> c) throws IOException {
    return (ApiResponse) parse(response, c);
  }

  private Object parse(HttpResponse response, Class<?> c) throws IOException {
    if (response.getStatusCode() == 204 || response.getContent() == null)
      return buildTypeSafe(c);

    try (InputStream in = response.getContent()) {
      JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
      return jsonParser.parse(c);
    }
  }

  @SuppressWarnings("deprecation")
  private ApiResponse buildTypeSafe(Class<?> c) {
    try {
      return (ApiResponse) c.newInstance();
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException("Cannot instantiate " + c, e);
    }
  }

  private String versionedPath(String path) {
    return Dnsimple.getApiBase() + API_VERSION_PATH + path;
  }

  private GenericUrl buildUrl(String url, Map<String, Object> options) {
    if (options == null) {
      options = Collections.emptyMap();
    }

    UrlBuilder urlBuilder = UrlBuilder.fromString(url);
    if (options.containsKey("filter")) {
      Filter filter = (Filter) options.remove("filter");
      urlBuilder = urlBuilder.addParameter(filter.name, filter.value);
    }

    if (options.size() > 0) {
      for (Map.Entry<String, Object> kv : options.entrySet()) {
        urlBuilder = urlBuilder.addParameter(kv.getKey(), kv.getValue().toString());
      }
    }

    return new GenericUrl(urlBuilder.toUrl());
  }

}

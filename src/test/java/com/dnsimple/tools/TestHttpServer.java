package com.dnsimple.tools;

import static java.net.URLDecoder.decode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;

import com.google.api.client.http.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestHttpServer implements Runnable {
  private final int port;
  private Status status = Status.IDLE;
  private Path fixturePath;
  private String expectedPath;
  private Map<String, String> expectedHeaders = emptyMap();
  private Map<String, Object> expectedJsonObject = emptyMap();
  private List<String> expectedJsonArray = emptyList();
  private HttpMethod expectedMethod;
  private UncheckedServerSocket server;

  public TestHttpServer(int port) {
    this.port = port;
  }

  public void run() {
    while (status == Status.RUNNING) {
      UncheckedSocket socket = server.accept();
      if (socket == null)
        continue;

      socket.readRequest();

      if (expectedMethod != null && expectedPath != null)
        closingSocketAssertions(socket, () -> {
          assertThat(socket.getMethod(), is(expectedMethod));
          assertThat(socket.getPath(), is(expectedPath));
        });

      if (!expectedHeaders.isEmpty())
        closingSocketAssertions(socket, () ->
            assertThat(expectedHeaders.entrySet(), everyItem(isIn(socket.getHeaders().entrySet())))
        );

      if (!expectedJsonObject.isEmpty())
        closingSocketAssertions(socket, () ->
            {
              Map<String, Object> actualPayload = socket.getJsonObjectPayload();
              assertThat(actualPayload.entrySet(), everyItem(isIn(expectedJsonObject.entrySet())));
              assertThat(actualPayload.size(), is(expectedJsonObject.size()));
            }
        );

      if (!expectedJsonArray.isEmpty())
        closingSocketAssertions(socket, () ->
            {
              List<String> actualPayload = socket.getJsonArrayPayload();
              assertThat(actualPayload, everyItem(isIn(expectedJsonArray)));
              assertThat(actualPayload.size(), is(expectedJsonArray.size()));
            }
        );


      if (fixturePath != null)
        socket.write(fixturePath);
      else
        socket.write204();
    }
  }

  private void closingSocketAssertions(UncheckedSocket socket, Runnable runnable) {
    try {
      runnable.run();
    } catch (Throwable t) {
      socket.write400();
      stop();
      throw t;
    }
  }

  public synchronized void start() {
    server = UncheckedServerSocket.at(port);
    status = Status.RUNNING;
    new Thread(this).start();
  }

  public synchronized void stop() {
    status = Status.STOPPED;
    server.close();
  }

  public void stubFixtureAt(String path) {
    stubFixture(resourcePath(path));
  }

  public void stubFixture(Path fixturePath) {
    this.fixturePath = fixturePath;
  }

  public void expectDelete(String path) {
    expect(HttpMethod.DELETE, path);
  }

  public void expectGet(String path) {
    expect(HttpMethod.GET, path);
  }

  public void expectPost(String path) {
    expect(HttpMethod.POST, path);
  }

  public void expectPut(String path) {
    expect(HttpMethod.PUT, path);
  }

  public void expectPatch(String path) {
    expect(HttpMethod.PATCH, path);
  }

  private void expect(HttpMethod method, String path) {
    this.expectedPath = decodePath(path);
    this.expectedMethod = method;
  }

  @SuppressWarnings("unchecked")
  public void expectHeaders(HttpHeaders headers) {
    this.expectedHeaders = headers.entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            e -> String.join(";", ((List<String>) e.getValue()))
        ));
  }

  public void expectJsonPayload(Map<String, Object> jsonPayload) {
    this.expectedJsonObject = jsonPayload;
  }

  public void expectJsonPayload(List<String> jsonPayload) {
    this.expectedJsonArray = jsonPayload;
  }

  private String decodePath(String path) {
    if (!path.startsWith("http"))
      return decodeEntities(path);

    try {
      URL url = new URL(path);
      return url.getQuery() == null ? url.getPath() : url.getPath() + "?" + decodeEntities(url.getQuery());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private String decodeEntities(String path) {
    try {
      return decode(path, UTF_8.displayName());
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public String getBaseURL() {
    return "http://localhost:" + port;
  }

  private Path resourcePath(String resourceFile) {
    try {
      String relativeName = resourceFile.startsWith("/") ? resourceFile.substring(1) : resourceFile;
      return Paths.get(TestHttpServer.class.getResource("/com/dnsimple/" + relativeName).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public void reset() {
    fixturePath = null;
    expectedPath = null;
    expectedHeaders = emptyMap();
    expectedJsonObject = emptyMap();
    expectedJsonArray = emptyList();
    expectedMethod = null;
  }
}

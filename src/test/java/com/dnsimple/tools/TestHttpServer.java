package com.dnsimple.tools;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHttpServer implements Runnable {
  private final int port;
  private Status status = Status.IDLE;
  private Path fixturePath;
  private UncheckedServerSocket server;
  private RecordedRequest recordedRequest;

  public TestHttpServer(int port) {
    this.port = port;
  }

  public RecordedRequest getRecordedRequest() {
    return recordedRequest;
  }

  public void run() {
    while (status == Status.RUNNING) {
      UncheckedSocket socket = server.accept();
      if (socket == null)
        continue;

      recordedRequest = socket.readRequest();

      if (fixturePath != null)
        socket.write(fixturePath);
      else
        socket.write204();
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
    this.fixturePath = resourcePath(path);
  }

  public String getBaseURL() {
    return "http://localhost:" + port;
  }

  public void reset() {
    fixturePath = null;
    recordedRequest = null;
  }

  private Path resourcePath(String resourceFile) {
    try {
      String relativeName = resourceFile.startsWith("/") ? resourceFile.substring(1) : resourceFile;
      return Paths.get(TestHttpServer.class.getResource("/com/dnsimple/" + relativeName).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}

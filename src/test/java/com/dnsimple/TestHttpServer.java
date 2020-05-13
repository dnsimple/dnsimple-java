package com.dnsimple;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestHttpServer {
  private final int port;
  private final Thread t;
  private Status status = Status.IDLE;
  private String rawHttpResponse;

  public TestHttpServer(int port) {
    this.port = port;
    t = new Thread(() -> {
      try (ServerSocket socket = new ServerSocket(port)) {
        while (status == Status.RUNNING) {
          Socket connection = socket.accept();
          try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            if (in.readLine() == null)
              continue;

            PrintStream pout = new PrintStream(new BufferedOutputStream(connection.getOutputStream()));
            pout.print(rawHttpResponse);
            pout.close();
          } catch (Throwable e) {
            throw new RuntimeException(e);
          }
        }
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    });
  }

  public void start() {
    t.start();
    status = Status.RUNNING;
  }

  public void stop() {
    status = Status.STOPPED;
  }

  public void stubFixture(Path path) {
    try {
      this.rawHttpResponse = new String(Files.readAllBytes(path));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public String getBaseURL() {
    return "http://localhost:" + port;
  }

  private enum Status {
    IDLE, RUNNING, STOPPED;
  }
}

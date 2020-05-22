package com.dnsimple.tools;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class UncheckedSocket {
  private final Socket socket;

  public UncheckedSocket(Socket socket) {
    this.socket = socket;
  }

  RecordedRequest readRequest() {
    try {
      InputStream is = socket.getInputStream();
      InputStreamReader isReader = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isReader);

      // Read the header
      String path = null;
      HttpMethod method = null;
      Map<String, String> headers = new HashMap<>();
      String line;
      boolean firstLine = true;
      while ((line = br.readLine()).length() != 0) {
        if (firstLine) {
          StringTokenizer tokenizer = new StringTokenizer(line);
          method = HttpMethod.valueOf(tokenizer.nextToken());
          path = tokenizer.nextToken();
          firstLine = false;
        } else {
          String[] parts = line.split(":");
          headers.put(parts[0].trim(), parts[1].trim());
        }
      }

      // Read the payload
      StringBuilder payload = new StringBuilder();
      while (br.ready())
        payload.append((char) br.read());

      return new RecordedRequest(method, path, headers, payload.toString());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void write(Path fixturePath) {
    try {
      System.out.println(new String(Files.readAllBytes(fixturePath), UTF_8));
      Files.copy(fixturePath, socket.getOutputStream());
      socket.shutdownOutput();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void write(String... lines) {
    try (PrintWriter pout = new PrintWriter(socket.getOutputStream())) {
      for (String line : lines) {
        System.out.println(line);
        pout.println(line);
      }
      System.out.println();
      pout.println();
      pout.flush();
      socket.shutdownOutput();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

  }

  public void write204() {
    write(
        "HTTP/1.1 204 No Content",
        "Server: Test HTTP Server",
        "Date: " + OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME),
        "Connection: close",
        "Content-type: text/plain",
        "Content-length: 0"
    );
  }
}

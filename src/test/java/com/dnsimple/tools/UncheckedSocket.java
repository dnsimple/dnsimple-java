package com.dnsimple.tools;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class UncheckedSocket {
  private final Socket socket;
  private final Map<String, String> headers = new HashMap<>();
  private HttpMethod method;
  private String path;
  private String jsonPayload;

  public UncheckedSocket(Socket socket) {
    this.socket = socket;
  }

  void readRequest() {
    try {
      InputStream is = socket.getInputStream();
      InputStreamReader isReader = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isReader);

      // Read the header
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
          headers.put(parts[0].trim().toLowerCase(), parts[1].trim());
        }
      }

      System.out.println();

      // Read the payload
      StringBuilder payload = new StringBuilder();
      while (br.ready()) {
        payload.append((char) br.read());
      }
      jsonPayload = payload.toString();
      System.out.println(jsonPayload);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public HttpMethod getMethod() {
    return method;
  }

  public String getPath() {
    return path;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public Map<String, Object> getJsonObjectPayload() {
    Type type = new TypeToken<HashMap<String, Object>>() {
    }.getType();

    return new Gson().fromJson(jsonPayload, type);
  }

  public List<String> getJsonArrayPayload() {
    Type type = new TypeToken<ArrayList<String>>() {
    }.getType();

    return new Gson().fromJson(jsonPayload, type);
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

  public void write400() {
    write(
        "HTTP/1.1 400 Bad Request",
        "Server: Test HTTP Server",
        "Date: " + OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME),
        "Connection: close",
        "Content-type: text/plain",
        "Content-length: 0"
    );
  }
}

package com.dnsimple.tools;

import com.dnsimple.endpoints.http.HttpMethod;

import java.io.*;
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
            String payload;
            int bytesToRead = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
            if (bytesToRead > 0) {
                StringBuilder payloadBuilder = new StringBuilder();
                char[] buffer = new char[1000];
                int bytesRead;
                boolean end = false;
                while (!end) {
                    bytesRead = br.read(buffer);
                    payloadBuilder.append(new String(buffer, 0, bytesRead));
                    if (payloadBuilder.length() == bytesToRead)
                        end = true;
                }
                payload = payloadBuilder.toString();
            } else
                payload = "";
            return new RecordedRequest(method, path, headers, payload);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void write(Path fixturePath) {
        try {
            Files.copy(fixturePath, socket.getOutputStream());
            socket.shutdownOutput();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void write(String... lines) {
        try (PrintWriter pout = new PrintWriter(socket.getOutputStream())) {
            for (String line : lines)
                pout.println(line);
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

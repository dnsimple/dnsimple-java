package com.dnsimple.tools;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;

class UncheckedServerSocket {
  private final ServerSocket server;

  UncheckedServerSocket(ServerSocket server) {
    this.server = server;
  }

  static UncheckedServerSocket at(int port) {
    try {
      return new UncheckedServerSocket(new ServerSocket(port));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public UncheckedSocket accept() {
    try {
      return new UncheckedSocket(server.accept());
    } catch (IOException ignored) {
      return null;
    }
  }

  public void close() {
    uncheckedClose();
    waitUntilClosed();
  }

  private void waitUntilClosed() {
    AtomicBoolean closed = new AtomicBoolean(false);
    Thread notifier = new Thread(() -> {
      try {
        Thread.sleep(20);
        closed.set(server.isClosed());
        synchronized (this) {
          this.notify();
        }
      } catch (InterruptedException ignored) {
      }
    });
    notifier.start();
    while (!closed.get()) {
      try {
        synchronized (this) {
          wait();
        }
      } catch (InterruptedException ignored) {
      }
    }
  }

  private void uncheckedClose() {
    try {
      server.close();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}

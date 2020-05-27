package com.dnsimple.tools;

@FunctionalInterface
public interface ThrowingRunnable2<T extends Throwable, U extends Throwable> {
  void run() throws T, U;
}

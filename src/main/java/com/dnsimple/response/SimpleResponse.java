package com.dnsimple.response;

public class SimpleResponse<T> extends ApiResponse<T> {
    private final T data;

    public SimpleResponse(T data) {
        this.data = data;
    }

    public static <U> SimpleResponse<U> empty() {
        return new SimpleResponse<>(null);
    }

    public T getData() {
        return data;
    }
}

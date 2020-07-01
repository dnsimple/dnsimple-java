package com.dnsimple.data;

public class User {
    private final Integer id;
    private final String email;

    public User(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}


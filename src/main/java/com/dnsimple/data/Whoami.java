package com.dnsimple.data;

public class Whoami {
    private final Account account;
    private final User user;

    public Whoami(Account account, User user) {
        this.account = account;
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }
}

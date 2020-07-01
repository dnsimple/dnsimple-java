package com.dnsimple.data;

public class Whoami {
    private Account account;
    private User user;

    public Whoami() {
    }

    public Whoami(Account account) {
        this.account = account;
    }

    public Whoami(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }
}

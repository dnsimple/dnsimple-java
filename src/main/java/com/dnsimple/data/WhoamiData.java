package com.dnsimple.data;

public class WhoamiData {
    private final Account account;
    private final User user;

    public WhoamiData(Account account, User user) {
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

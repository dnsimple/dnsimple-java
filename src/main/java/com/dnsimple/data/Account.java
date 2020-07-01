package com.dnsimple.data;


public class Account {

  private Integer id;


  private String email;

  public Account() {
  }

  public Account(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

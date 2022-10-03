package com.ug.air.alrite.Models;

public class Cred {

    String username;
    String token;
    String password;
    int period;

    public Cred(String username, String token, int period, String password) {
        this.username = username;
        this.token = token;
        this.period = period;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public int getPeriod() {
        return period;
    }

    public String getPassword() {
        return password;
    }
}

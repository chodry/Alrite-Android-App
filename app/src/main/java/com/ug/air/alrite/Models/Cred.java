package com.ug.air.alrite.Models;

public class Cred {

    String username;
    String token;
    int period;

    public Cred(String username, String token, int period) {
        this.username = username;
        this.token = token;
        this.period = period;
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
}

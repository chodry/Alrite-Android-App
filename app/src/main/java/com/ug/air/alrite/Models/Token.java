package com.ug.air.alrite.Models;

import com.google.gson.annotations.SerializedName;

public class Token {

    String token;
    @SerializedName("user_info")
    Information information;

    public Token(String token, Information information) {
        this.token = token;
        this.information = information;
    }

    public String getToken() {
        return token;
    }

    public Information getInformation() {
        return information;
    }
}

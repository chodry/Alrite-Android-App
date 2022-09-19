package com.ug.air.alrite.Models;

public class Information {

    String code;
    String healthy_facility;

    public Information(String code, String healthy_facility) {
        this.code = code;
        this.healthy_facility = healthy_facility;
    }

    public String getCode() {
        return code;
    }

    public String getHealthy_facility() {
        return healthy_facility;
    }
}

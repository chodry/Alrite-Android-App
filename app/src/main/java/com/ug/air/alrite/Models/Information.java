package com.ug.air.alrite.Models;

public class Information {

    String code;
    String healthy_facility;
    int study_id;

    public Information(String code, String healthy_facility, int study_id) {
        this.code = code;
        this.healthy_facility = healthy_facility;
        this.study_id = study_id;
    }

    public String getCode() {
        return code;
    }

    public String getHealthy_facility() {
        return healthy_facility;
    }

    public int getStudy_id() {
        return study_id;
    }
}

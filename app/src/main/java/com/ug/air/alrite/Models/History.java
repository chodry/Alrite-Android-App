package com.ug.air.alrite.Models;

public class History {

    private String diagnosis;
    private String date;

    public History(String diagnosis, String date) {
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDate() {
        return date;
    }
}

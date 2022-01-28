package com.ug.air.alrite.Models;

public class Patient {

    private String name;
    private String number;
    private String initial;
    private String fileName;

    public Patient(String name, String number, String initial, String fileName) {
        this.name = name;
        this.number = number;
        this.initial = initial;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getInitial() {
        return initial;
    }

    public String getFileName() {
        return fileName;
    }
}

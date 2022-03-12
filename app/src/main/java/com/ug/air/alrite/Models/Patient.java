package com.ug.air.alrite.Models;

public class Patient {

    private String age;
    private String sex;
    private String initial;
    private String parent;
    private String date;

    public Patient(String age, String sex, String initial, String parent, String date) {
        this.age = age;
        this.sex = sex;
        this.initial = initial;
        this.parent = parent;
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getInitial() {
        return initial;
    }

    public String getParent() {
        return parent;
    }

    public String getDate() {
        return date;
    }
}

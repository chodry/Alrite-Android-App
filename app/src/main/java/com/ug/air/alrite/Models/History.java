package com.ug.air.alrite.Models;

public class History {

    private String age;
    private String sex;
    private String initial;
    private String parent;
    private String date;
    private String filename;
    private String pending;
    private String incomplete;

    public History(String age, String sex, String initial, String parent, String date, String filename, String pending, String incomplete) {
        this.age = age;
        this.sex = sex;
        this.initial = initial;
        this.parent = parent;
        this.date = date;
        this.filename = filename;
        this.pending = pending;
        this.incomplete = incomplete;
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

    public String getFilename() {
        return filename;
    }

    public String getPending() {
        return pending;
    }

    public String getIncomplete() {
        return incomplete;
    }
}

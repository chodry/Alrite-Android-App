package com.ug.air.alrite.Models;

import java.util.List;

public class Diagnosis {

    private String diagnosis;
    private List<Assessment> assessmentList;

    public Diagnosis(String diagnosis, List<Assessment> assessmentList) {
        this.diagnosis = diagnosis;
        this.assessmentList = assessmentList;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public List<Assessment> getAssessmentList() {
        return assessmentList;
    }
}

package com.ug.air.alrite.Models;

import java.util.List;

public class Diagnosis {

    private Integer diagnosis;
    private List<Assessment> assessmentList;

    public Diagnosis(Integer diagnosis, List<Assessment> assessmentList) {
        this.diagnosis = diagnosis;
        this.assessmentList = assessmentList;
    }

    public Integer getDiagnosis() {
        return diagnosis;
    }

    public List<Assessment> getAssessmentList() {
        return assessmentList;
    }
}

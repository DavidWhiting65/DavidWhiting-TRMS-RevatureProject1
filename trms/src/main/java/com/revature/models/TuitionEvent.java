package com.revature.models;

public class TuitionEvent {

    // instance variables
    public int tuitionTypeId;
    public String tuitionType;
    public float coveragePercent;
    public String gradingFormat;

    // constructors


    public TuitionEvent() {
    }

    public TuitionEvent(String tuitionType, float coveragePercent, String gradingFormat) {
        this.tuitionType = tuitionType;
        this.coveragePercent = coveragePercent;
        this.gradingFormat = gradingFormat;
    }

    public TuitionEvent(int tuitionTypeId, String tuitionType, float coveragePercent, String gradingFormat) {
        this.tuitionTypeId = tuitionTypeId;
        this.tuitionType = tuitionType;
        this.coveragePercent = coveragePercent;
        this.gradingFormat = gradingFormat;
    }

    // getter and setters


    public int getTuitionTypeId() {
        return tuitionTypeId;
    }

    public void setTuitionTypeId(int tuitionTypeId) {
        this.tuitionTypeId = tuitionTypeId;
    }

    public String getTuitionType() {
        return tuitionType;
    }

    public void setTuitionType(String tuitionType) {
        this.tuitionType = tuitionType;
    }

    public float getCoveragePercent() {
        return coveragePercent;
    }

    public void setCoveragePercent(float coveragePercent) {
        this.coveragePercent = coveragePercent;
    }

    public String getGradingFormat() {
        return gradingFormat;
    }

    public void setGradingFormat(String gradingFormat) {
        this.gradingFormat = gradingFormat;
    }

    @Override
    public String toString() {
        return "TuitionEvent{" +
                "tuitionType='" + tuitionType + '\'' +
                ", coveragePercent=" + coveragePercent +
                ", gradingFormat='" + gradingFormat + '\'' +
                '}';
    }
}

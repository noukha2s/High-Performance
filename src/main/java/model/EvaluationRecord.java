package model;

import org.bson.Document;

public class EvaluationRecord {
    private int goalID;
    private String goalDescription;
    private int targetValue;
    private int actualValue;
    private int year;
    private int salesManID;

    public int getSalesManID() {
        return salesManID;
    }

    public void setSalesManID(int salesManID) {
        this.salesManID = salesManID;
    }


    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public int getActualValue() {
        return actualValue;
    }

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EvaluationRecord(int goalID, String goalDescription, int targetValue, int actualValue, int year) {
        this.goalID = goalID;
        this.goalDescription = goalDescription;
        this.targetValue = targetValue;
        this.actualValue = actualValue;
        this.year = year;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("goalID", this.goalID);
        document.append("goalDescription", this.goalDescription);
        document.append("targetValue", this.targetValue);
        document.append("actualValue", this.actualValue);
        document.append("targetValue", this.targetValue);
        document.append("year", this.year);
        return document;
    }
}

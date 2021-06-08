package com.uniapp.r2scalendar.Model;

public class ClassResponse {
    private int classID;
    private String className;
    private int capacity;
    private String startTime;
    private String endTime;
    private int isDeleted;

    public ClassResponse() {
    }

    public ClassResponse(int classID, String className, int capacity, String startTime, String endTime, int isDeleted) {
        this.classID = classID;
        this.className = className;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
    }

    public ClassResponse(String className, int capacity, String startTime, String endTime) {
        this.className = className;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int isDeleted() {
        return isDeleted;
    }

    public void setDeleted(int deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return className;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ClassResponse){
            ClassResponse c = (ClassResponse )obj;
            if(c.getClassName().equals(className)) return true;
        }

        return false;
    }
}

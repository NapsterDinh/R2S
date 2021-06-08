package com.uniapp.r2scalendar.Model;

public class AssignmentResponse {
    private String moduleName;
    private String className;
    private String trainerName;
    private String registrationCode;

    public AssignmentResponse(String moduleName, String className, String trainerName, String registrationCode) {
        this.moduleName = moduleName;
        this.className = className;
        this.trainerName = trainerName;
        this.registrationCode = registrationCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}

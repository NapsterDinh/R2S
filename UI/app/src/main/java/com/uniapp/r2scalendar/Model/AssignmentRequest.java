package com.uniapp.r2scalendar.Model;

public class AssignmentRequest {
    private int idModule;
    private int idClass;
    private String idTrainer;
    private String registrationCode;

    public AssignmentRequest(int idModule, int idClass, String idTrainer, String registrationCode) {
        this.idModule = idModule;
        this.idClass = idClass;
        this.idTrainer = idTrainer;
        this.registrationCode = registrationCode;
    }

    public AssignmentRequest() {
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(String idTrainer) {
        this.idTrainer = idTrainer;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}

package com.uniapp.r2scalendar.Model;

public class TrainerResponse {
    private String userName;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int isActive;
    private String password;
    private int idSkill;
    private String activationCode;
    private String resetPasswordCode;
    private int isReceiveNotification;

    public TrainerResponse() {
    }

    public TrainerResponse(String userName, String email, String phone, String address, String name, int isActive, String password,
                           int idSkill, String activationCode, String resetPasswordCode, int isReceiveNotification) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
        this.password = password;
        this.idSkill = idSkill;
        this.activationCode = activationCode;
        this.resetPasswordCode = resetPasswordCode;
        this.isReceiveNotification = isReceiveNotification;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int isActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public int isReceiveNotification() {
        return isReceiveNotification;
    }

    public void setReceiveNotification(int receiveNotification) {
        isReceiveNotification = receiveNotification;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TrainerResponse){
            TrainerResponse c = (TrainerResponse )obj;
            if(c.getName().equals(name)) return true;
        }

        return false;
    }
}

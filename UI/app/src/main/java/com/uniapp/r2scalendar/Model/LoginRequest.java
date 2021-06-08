package com.uniapp.r2scalendar.Model;

public class LoginRequest {
    private String UserName;
    private String Password;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;
    private int IsActive;
    private String ActivationCode;
    private String ResetPasswordCode;

    public LoginRequest(String userName, String password, String name, String email, String phone, String address, int isActive, String activationCode, String resetPasswordCode) {
        UserName = userName;
        Password = password;
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        IsActive = isActive;
        ActivationCode = activationCode;
        ResetPasswordCode = resetPasswordCode;
    }

    public LoginRequest()
    {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getActive() {
        return IsActive;
    }

    public void setActive(int active) {
        IsActive = active;
    }

    public String getActivationCode() {
        return ActivationCode;
    }

    public void setActivationCode(String activationCode) {
        ActivationCode = activationCode;
    }

    public String getResetPasswordCode() {
        return ResetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        ResetPasswordCode = resetPasswordCode;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String username) {
        UserName = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

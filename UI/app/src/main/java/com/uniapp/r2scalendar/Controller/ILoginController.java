package com.uniapp.r2scalendar.Controller;

import com.uniapp.r2scalendar.Model.LoginRequest;

import java.util.List;

public interface ILoginController {
    void getAllTrainee(String Username, String Password);
    void onResult(List<LoginRequest> response);

    void onFailureResponse(String message);

    void getAllTrainer(String Username, String Password);

    void getAllAdmin(String Username, String Password);
}

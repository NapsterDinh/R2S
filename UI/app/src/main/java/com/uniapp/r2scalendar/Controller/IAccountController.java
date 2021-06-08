package com.uniapp.r2scalendar.Controller;

import com.uniapp.r2scalendar.Model.Trainee;
import com.uniapp.r2scalendar.View.ILoginView;
import com.uniapp.r2scalendar.View.ISignUpView;

public interface IAccountController {
    void OnSignUp(Trainee user);
    void OnSignUpSuccess(String message, ILoginView loginView , ISignUpView signUpView);
}

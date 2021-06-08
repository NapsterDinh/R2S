package com.uniapp.r2scalendar.Controller;

import android.text.TextUtils;
import android.util.Patterns;

import com.uniapp.r2scalendar.Model.Trainee;
import com.uniapp.r2scalendar.Service.UserService;
import com.uniapp.r2scalendar.View.ILoginView;
import com.uniapp.r2scalendar.View.ISignUpView;

public class AccountController implements IAccountController {

    ILoginView loginView;
    ISignUpView signUpView;

    public AccountController(ILoginView loginView, ISignUpView signUpView) {
        this.loginView = loginView;
        this.signUpView = signUpView;
    }

    @Override
    public void OnSignUp(Trainee user) {
        UserService userService = new UserService(this, null, signUpView);

        if (isValid(user.getEmail())) {
            userService.SignUp(user);
        }
    }

    /* Handling Events */
    @Override
    public void OnSignUpSuccess(String message,ILoginView loginView ,ISignUpView signUpView) {
        if (loginView != null) {
            signUpView.OnSignUpSuccess(message);
        } else {
            signUpView.OnSignUpSuccess(message);
        }
    }

    private boolean isValid(String email) {
    if (TextUtils.isEmpty(email)
            || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return false;
    }
    return true;
}
}

package com.uniapp.r2scalendar.Controller;

import android.os.Handler;
import android.os.Looper;

import com.uniapp.r2scalendar.Model.LoginRequest;
import com.uniapp.r2scalendar.Service.LoginService;
import com.uniapp.r2scalendar.View.ILoginView;

import java.util.List;

public class LoginController implements ILoginController{

    LoginService loginService;
    ILoginView iLoginView;
    Handler handler;


    public LoginController(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        handler = new Handler(Looper.getMainLooper());
    }

    public LoginController() {

    }
    @Override
    public void getAllTrainee(String Username, String Password) {
        //call api
        iLoginView.displayProgressDialog();
        loginService = new LoginService( this, iLoginView);
        loginService.getAllTrainee(Username, Password);
    }
    @Override
    public void getAllTrainer(String Username, String Password) {
        //call api
        iLoginView.displayProgressDialog();
        loginService = new LoginService( this, iLoginView);
        loginService.getAllTrainer(Username, Password);
    }

    @Override
    public void getAllAdmin(String Username, String Password) {
        iLoginView.displayProgressDialog();
        loginService = new LoginService( this, iLoginView);
        loginService.getAllAdmin(Username, Password);
    }


    @Override
    public void onResult(List<LoginRequest> response) {
        if(response.size() == 0) {
            iLoginView.showDialog();
        }
        else iLoginView.onResult(response.get(0));
    }

    @Override
    public void onFailureResponse(String message) {
        iLoginView.onResponseFailed(message);
    }

}

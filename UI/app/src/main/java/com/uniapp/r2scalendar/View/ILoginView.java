package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.LoginRequest;

public interface ILoginView {
    void onResult(LoginRequest response);
    void onResponseFailed(String message);
    void showDialog();
    void displayProgressDialog();
    void disableProgressDialog();
}

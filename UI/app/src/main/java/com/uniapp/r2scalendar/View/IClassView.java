package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;

import java.util.List;

public interface IClassView {
    void displayItem(View v, List<ClassResponse> classResponseList);
    void displayProgressDialog(View view);
    void disableProgressDialog();
    void onSuccess(String message);
}

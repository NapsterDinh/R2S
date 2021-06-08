package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.MessageResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;

import java.util.List;

public interface IAddClassView {
    void onSuccess(String message, MessageResponse messageResponse);
    void displayProgressDialog(View view);
    void disableProgressDialog();
}

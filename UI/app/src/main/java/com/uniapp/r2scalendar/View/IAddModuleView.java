package com.uniapp.r2scalendar.View;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Model.Feedback;
import com.uniapp.r2scalendar.Model.ModuleResponse;

import java.util.List;

public interface IAddModuleView {

    void loadSpinner(List<Admin> adminList, View view);
    void loadSpinnerFeedback(List<Feedback> feedbackList, View view);
    void displayItem(View v, List<ModuleResponse> moduleResponseList);
    void setAdminSelected();
    void setFeedbackTitleSelected();

    void onSuccess(String message);

    void displayProgressDialog(View view);

    void disableProgressDialog();
}

package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;

import java.util.List;

public interface IFeedbackView {
    void initVariable(View v);
    void setListSpinner(boolean isModule, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList);
    void onFailureResponse(String message);
}

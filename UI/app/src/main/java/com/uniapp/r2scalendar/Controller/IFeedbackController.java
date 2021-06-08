package com.uniapp.r2scalendar.Controller;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;

import java.util.List;

public interface IFeedbackController {
    void getListSpinner();
    void setListSpinner(boolean isModule, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList);
    void  onFailureResponse(String message);
}

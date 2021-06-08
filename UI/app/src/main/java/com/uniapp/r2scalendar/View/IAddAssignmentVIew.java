package com.uniapp.r2scalendar.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;

import java.util.List;

public interface IAddAssignmentVIew {
    void onResponseFailed(String message);

    void setListSpinner(String type ,List<TrainerResponse> trainerResponseList, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList);

    void configDialogAndShow(boolean isSuccess);
}

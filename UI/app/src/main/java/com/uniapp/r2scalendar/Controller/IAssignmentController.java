package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.AssignmentRequest;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;

import java.util.List;

public interface IAssignmentController {
    void searchAssignment(String str_search);
    void getAll();
    void displayItem(List<AssignmentResponse> assignmentResponseList);
    void onFailureResponse(String message);
    void onFailureResponseAddEdit(String message);
    void addAssignment(AssignmentRequest assignmentRequest);
    void responseAddEditAssignment(boolean response);
    void editAssignment(AssignmentRequest new_assignment, AssignmentRequest old_assignment);
    void deleteAssignment(boolean isFirstStep ,AssignmentResponse assignmentResponse);
    void configDialogConstraint(String response);
    void getListSpinner();
    void getListSpinnerTrainer();
    void setListSpinner(String typeSpinner, List<TrainerResponse> trainerResponseList, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList);
}

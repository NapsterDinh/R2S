package com.uniapp.r2scalendar.View;

import android.view.View;

import com.uniapp.r2scalendar.Model.AssignmentResponse;

import java.util.List;

public interface IAssignmentView {
    void initVariable(View v);
    void displayItem(View v, List<AssignmentResponse> assignmentResponseList);
    void onSearchSuccess(List<AssignmentResponse> assignmentResponseList);
    void onResponseFailed(String message);
    void errorEmptyEdtSearch();
    void configDialogConstraint(boolean isViolateConstraint);
}

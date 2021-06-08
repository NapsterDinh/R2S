package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.AssignmentRequest;
import com.uniapp.r2scalendar.Model.AssignmentResponse;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;
import com.uniapp.r2scalendar.Service.AssignmentService;
import com.uniapp.r2scalendar.Service.ClassService;
import com.uniapp.r2scalendar.Service.ModuleService;
import com.uniapp.r2scalendar.Service.TrainerService;
import com.uniapp.r2scalendar.Service.UserService;
import com.uniapp.r2scalendar.View.IAddAssignmentVIew;
import com.uniapp.r2scalendar.View.IAssignmentView;
import com.uniapp.r2scalendar.View.ILoginView;
import com.uniapp.r2scalendar.View.ISignUpView;

import java.util.List;

public class AssignmentController implements IAssignmentController{
    IAssignmentView iAssignmentView;
    IAddAssignmentVIew iAddAssignmentVIew;

    AssignmentService assignmentService;
    View view;
    List<ClassResponse> classResponseList;
    List<ModuleResponse> moduleResponseList;
    List<TrainerResponse> trainerResponseList;

    public AssignmentController(IAssignmentView iAssignmentView, View view) {
        this.iAssignmentView = iAssignmentView;
        this.view = view;
        if(assignmentService==null)
        {
            assignmentService = new AssignmentService(this, iAssignmentView);
        }
    }

    public AssignmentController(IAddAssignmentVIew iAddAssignmentVIew, View view) {
        this.iAddAssignmentVIew = iAddAssignmentVIew;
        this.view = view;
        if(assignmentService==null)
        {
            assignmentService = new AssignmentService(this, iAddAssignmentVIew);
        }
    }

    @Override
    public void searchAssignment(String str_search) {
        //call api
        if(str_search.equals(""))
        {
            assignmentService.search("Empty");
        }
        else
        {
            assignmentService.search(str_search);
        }

    }

    @Override
    public void getAll() {
        //call api
        assignmentService.getAll();
    }

    @Override
    public void displayItem(List<AssignmentResponse> assignmentResponseList) {
        iAssignmentView.displayItem(view,assignmentResponseList);
    }

    @Override
    public void onFailureResponse(String message) {
        iAssignmentView.onResponseFailed(message);
    }

    @Override
    public void onFailureResponseAddEdit(String message) {
        iAddAssignmentVIew.onResponseFailed(message);
    }

    @Override
    public void addAssignment(AssignmentRequest assignmentRequest) {
        //add assignment
        assignmentService.add(assignmentRequest);
    }

    @Override
    public void responseAddEditAssignment(boolean response) {
        iAddAssignmentVIew.configDialogAndShow(response);
    }

    @Override
    public void editAssignment(AssignmentRequest new_assignment, AssignmentRequest old_assignment) {
        assignmentService.edit(new_assignment,old_assignment);
    }

    @Override
    public void deleteAssignment(boolean isFirstStep, AssignmentResponse assignmentResponse) {
        assignmentService.delete(isFirstStep, assignmentResponse);
    }

    @Override
    public void configDialogConstraint(String response) {
        if(response.equals("Success"))
        {
            iAssignmentView.configDialogConstraint(false);
        }
        else
        {
            iAssignmentView.configDialogConstraint(true);
        }
    }

    @Override
    public void getListSpinner() {
        ModuleService moduleService = new ModuleService(this);
        moduleService.getSpinnerModuleForAssignment();

        ClassService classService = new ClassService(this);
        classService.getSpinnerClassForAssignment();

        getListSpinnerTrainer();
    }

    @Override
    public void getListSpinnerTrainer() {
        TrainerService trainerService = new TrainerService(this);
        trainerService.getSpinnerTrainerForAssignment();
    }

    @Override
    public void setListSpinner(String typeSpinner,List<TrainerResponse> trainerResponseList, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList) {
        switch (typeSpinner)
        {
            case "Module":
                this.moduleResponseList = moduleResponseList;
                iAddAssignmentVIew.setListSpinner("Module",trainerResponseList,classResponseList,moduleResponseList);
                break;
            case "Class":
                this.classResponseList = classResponseList;
                iAddAssignmentVIew.setListSpinner("Class",trainerResponseList,classResponseList,moduleResponseList);
                break;
            case "Trainer":
                this.trainerResponseList = trainerResponseList;
                iAddAssignmentVIew.setListSpinner("Trainer",trainerResponseList,classResponseList,moduleResponseList);
                break;
        }
    }






}

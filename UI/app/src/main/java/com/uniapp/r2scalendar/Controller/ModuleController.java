package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Service.AdminService;
import com.uniapp.r2scalendar.Service.ClassService;
import com.uniapp.r2scalendar.Service.FeedbackService;
import com.uniapp.r2scalendar.Service.ModuleService;
import com.uniapp.r2scalendar.View.IAddClassView;
import com.uniapp.r2scalendar.View.IAddModuleView;
import com.uniapp.r2scalendar.View.IModuleView;
import com.uniapp.r2scalendar.ui.ModuleFragment;

import java.util.List;

public class ModuleController implements IModuleController{
    IModuleView moduleView;
    IAddModuleView addModuleView;
    View view;
    ModuleService moduleService;
    AdminService adminService;
    FeedbackService feedbackService;

    public ModuleController(IModuleView moduleView, View view) {
        this.moduleView = moduleView;
        this.addModuleView = addModuleView;
        this.view = view;
        moduleService = new ModuleService(moduleView, view);
        adminService = new AdminService(addModuleView, view);
        feedbackService = new FeedbackService(addModuleView, view);
    }
    @Override
    public void displayItem() {
        moduleView.displayProgressDialog(view);
        moduleService.getAllModule();
    }

    @Override
    public void handleDeleteModuleItem(String ModuleID, IModuleView addModuleView, View view) {
        moduleView.displayProgressDialog(view);
        moduleService.handleDeleteModule(ModuleID, moduleView);
    }

    @Override
    public void handleModuleItem(ModuleResponse moduleResponse, IAddModuleView addModuleView, View view) {
        addModuleView.displayProgressDialog(view);
        if (moduleResponse.getModuleId() != 0) {
            moduleResponse.setStartTime(moduleResponse.getStartTime().replace("/", "-"));
            moduleResponse.setEndTime(moduleResponse.getEndTime().replace("/", "-"));
            moduleResponse.setFeedbackStartTime(moduleResponse.getFeedbackStartTime().replace("/", "-"));
            moduleResponse.setFeedbackEndTime(moduleResponse.getEndTime().replace("/", "-"));


            moduleService.handleEditModule(moduleResponse, addModuleView);
        } else
            moduleService.handleAddModule(moduleResponse, addModuleView);
    }
    @Override
    public void getList(IAddModuleView addModuleView,View view) {
        adminService.getAllUserIDforSpiner(addModuleView, view);
    }
    @Override
    public void getListFeedback(IAddModuleView addModuleView,View view) {
        feedbackService.getAllFeedbackTitleforSpiner(addModuleView, view);
    }
}

package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Service.AssignmentService;
import com.uniapp.r2scalendar.Service.FeedbackService;
import com.uniapp.r2scalendar.View.IAddAssignmentVIew;
import com.uniapp.r2scalendar.View.IFeedbackView;

import java.util.List;

public class FeedBackController implements IFeedbackController {

    IFeedbackView iFeedbackView;
    View view;
    FeedbackService feedbackService;

    public FeedBackController(IFeedbackView iFeedbackView, View view) {
        this.iFeedbackView = iFeedbackView;
        this.view = view;
        if(feedbackService==null)
        {
            feedbackService = new FeedbackService(this, iFeedbackView);
        }
    }

    @Override
    public void getListSpinner() {
        feedbackService.getAll();
    }

    @Override
    public void setListSpinner(boolean isModule, List<ClassResponse> classResponseList, List<ModuleResponse> moduleResponseList) {
        iFeedbackView.setListSpinner(isModule,classResponseList,moduleResponseList);
    }

    @Override
    public void onFailureResponse(String message) {
        iFeedbackView.onFailureResponse(message);
    }
}

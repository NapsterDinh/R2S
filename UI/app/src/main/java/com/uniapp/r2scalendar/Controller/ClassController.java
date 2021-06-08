package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Service.ClassService;
import com.uniapp.r2scalendar.View.IAddClassView;
import com.uniapp.r2scalendar.View.IClassView;

public class ClassController implements IClassController {
    IClassView classView;
    View view;
    ClassService classService;

    public ClassController(IClassView classView, View view) {
        this.classView = classView;
        this.view = view;
        classService = new ClassService(classView, view);
    }
    @Override
    public void displayAllItem() {
        classView.displayProgressDialog(view);
        classService.getAllClass();
    }

    @Override
    public void handleClassItem(ClassResponse classResponse, IAddClassView addClassView, View view) {
        addClassView.displayProgressDialog(view);
        if (classResponse.getClassID() != 0) {
            classResponse.setStartTime(classResponse.getStartTime().replace("/", "-"));
            classResponse.setEndTime(classResponse.getEndTime().replace("/", "-"));

            classService.handleEditClass(classResponse, addClassView);
        } else
             classService.handleAddClass(classResponse, addClassView);
    }

    @Override
    public void handleDeleteClassItem(String ClassID, IClassView classView, View view) {
        classView.displayProgressDialog(view);
        classService.handleDeleteClass(ClassID, classView);
    }

}

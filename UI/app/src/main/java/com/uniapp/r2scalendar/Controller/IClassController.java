package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.View.IAddClassView;
import com.uniapp.r2scalendar.View.IClassView;
import com.uniapp.r2scalendar.adapter.ClassAdapter;

public interface IClassController {
    void displayAllItem();
    void handleClassItem(ClassResponse classResponse, IAddClassView addClassView, View view);
    void handleDeleteClassItem(String ClassID, IClassView addClassView, View view);
}

package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.View.IAddModuleView;
import com.uniapp.r2scalendar.View.IClassView;
import com.uniapp.r2scalendar.View.IModuleView;
import com.uniapp.r2scalendar.ui.AddModuleFragment;

import java.util.List;

public interface IModuleController {

    void getList(IAddModuleView addModuleView, View view);

    void getListFeedback(IAddModuleView addModuleView, View view);

    void displayItem();

    void handleDeleteModuleItem(String ModuleID, IModuleView addModuleView, View view);

    void handleModuleItem(ModuleResponse moduleResponse, IAddModuleView addModuleView, View view);
}

package com.uniapp.r2scalendar.Controller;

import android.view.View;

import com.uniapp.r2scalendar.Service.AdminService;
import com.uniapp.r2scalendar.View.IAddModuleView;
import com.uniapp.r2scalendar.View.IModuleView;

public class AdminController implements  IAdminController{
    private AdminService adminService;
    private IModuleView moduleView;
    private IAddModuleView addModuleView;
    View view;

    public AdminController(IAddModuleView addModuleView, View view) {
        this.addModuleView = addModuleView;
        this.view = view;
        adminService = new AdminService();
    }

    @Override
    public void getList() {
        adminService.getAllUserIDforSpiner(addModuleView, view);
    }
}

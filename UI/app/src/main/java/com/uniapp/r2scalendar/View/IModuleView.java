package com.uniapp.r2scalendar.View;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Model.ModuleResponse;

import java.util.List;

public interface IModuleView {
    void displayItem(View v, List<ModuleResponse> moduleResponseList);
    void disableProgressDialog();
    void displayProgressDialog(View view);
    void onSuccess(String message);
}

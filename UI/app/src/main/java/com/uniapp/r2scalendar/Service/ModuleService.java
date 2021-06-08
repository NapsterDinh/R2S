package com.uniapp.r2scalendar.Service;

import android.util.Log;
import android.view.View;

import com.uniapp.r2scalendar.Controller.IAssignmentController;
import com.uniapp.r2scalendar.Controller.IModuleController;
import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Model.ClassResponse;
import com.uniapp.r2scalendar.Model.ModuleResponse;
import com.uniapp.r2scalendar.Model.TrainerResponse;
import com.uniapp.r2scalendar.Repository.AdminRepository;
import com.uniapp.r2scalendar.Repository.ClassRepository;
import com.uniapp.r2scalendar.Repository.ModuleRepository;
import com.uniapp.r2scalendar.Repository.TrainerRepository;
import com.uniapp.r2scalendar.Utils.GlobalUser;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddModuleView;
import com.uniapp.r2scalendar.View.IClassView;
import com.uniapp.r2scalendar.View.IModuleView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleService {
    private IAssignmentController iAssignmentController;
    private IModuleView moduleView;
    private View view;
    private IModuleController iModuleController;

    HashMap<String,String> global_user;

    public ModuleService(IAssignmentController iAssignmentController){
        this.iAssignmentController = iAssignmentController;
        global_user = GlobalUser.getInstance();
    }

    public ModuleService(IModuleView moduleView, View view){
        this.moduleView = moduleView;
        this.view = view;
    }


    public void getSpinnerModuleForAssignment() {
        try {
            ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
            Call<List<ModuleResponse>> listCall = moduleRepository.getAll("false");
            listCall.enqueue(new Callback<List<ModuleResponse>>() {
                @Override
                public void onResponse(Call<List<ModuleResponse>> call, Response<List<ModuleResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    iAssignmentController.setListSpinner("Module",null,null,response.body());
                }

                @Override
                public void onFailure(Call<List<ModuleResponse>> call, Throwable t) {
                    iAssignmentController.onFailureResponseAddEdit(t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllModule() {
        try {
            ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
            Call<List<ModuleResponse>> listCall = moduleRepository.getAll();
            listCall.enqueue(new Callback<List<ModuleResponse>>() {
                @Override
                public void onResponse(Call<List<ModuleResponse>> call, Response<List<ModuleResponse>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    moduleView.displayItem(view, response.body());
                    moduleView.disableProgressDialog();
                }

                @Override
                public void onFailure(Call<List<ModuleResponse>> call, Throwable t) {
                    iAssignmentController.onFailureResponseAddEdit(t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAllAdminIDforspiner(IAddModuleView addModuleView, View view) {
        try {
            AdminRepository adminRepository = RetrofitClient.Client().create(AdminRepository.class);
            Call<List<Admin>> adminCall = adminRepository.getAll();
            adminCall.enqueue(new Callback<List<Admin>>() {
                @Override
                public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addModuleView.loadSpinner(response.body(), view);
                    addModuleView.setAdminSelected();
                }

                @Override
                public void onFailure(Call<List<Admin>> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteModuleItem(String ModuleID, IModuleView moduleView) {
        try {
            ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
            Call<ModuleResponse> listCall = moduleRepository.deleteModule(ModuleID);
            listCall.enqueue(new Callback<ModuleResponse>() {
                @Override
                public void onResponse(Call<ModuleResponse> call, Response<ModuleResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    moduleView.disableProgressDialog();
                    moduleView.onSuccess("Delete success!");
                }
                @Override
                public void onFailure(Call<ModuleResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAddModule(ModuleResponse moduleResponse, IAddModuleView addModuleView) {
        try {
            ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
            Call<ModuleResponse> listCall = moduleRepository.insertModule(moduleResponse);
            listCall.enqueue(new Callback<ModuleResponse>() {
                @Override
                public void onResponse(Call<ModuleResponse> call, Response<ModuleResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addModuleView.disableProgressDialog();
                    addModuleView.onSuccess("Add Success!");
                }

                @Override
                public void onFailure(Call<ModuleResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEditModule(ModuleResponse moduleResponse, IAddModuleView addModuleView) {
        try {
            ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
            Call<ModuleResponse> listCall = moduleRepository.editModule(moduleResponse);
            listCall.enqueue(new Callback<ModuleResponse>() {
                @Override
                public void onResponse(Call<ModuleResponse> call, Response<ModuleResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    addModuleView.disableProgressDialog();
                    addModuleView.onSuccess("Edit Success!");
                }

                @Override
                public void onFailure(Call<ModuleResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteModule(String ModuleID, IModuleView moduleView) {
        try {
            ModuleRepository moduleRepository = RetrofitClient.Client().create(ModuleRepository.class);
            Call<ModuleResponse> listCall = moduleRepository.deleteModule(ModuleID);
            listCall.enqueue(new Callback<ModuleResponse>() {
                @Override
                public void onResponse(Call<ModuleResponse> call, Response<ModuleResponse> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    moduleView.disableProgressDialog();
                    moduleView.onSuccess("Delete success!");
                }
                @Override
                public void onFailure(Call<ModuleResponse> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

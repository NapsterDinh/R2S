package com.uniapp.r2scalendar.Service;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.uniapp.r2scalendar.Model.Admin;
import com.uniapp.r2scalendar.Repository.AdminRepository;
import com.uniapp.r2scalendar.Repository.ModuleRepository;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.IAddModuleView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminService {

    public AdminService() {

    }

    public AdminService(IAddModuleView addModuleView, View view) {
    }

    public void getAllUserIDforSpiner(IAddModuleView addModuleView, View view) {
        try {
            AdminRepository adminRepository = RetrofitClient.Client().create(AdminRepository.class);
            Call<List<Admin>> adminCall = adminRepository.getAll();
            adminCall.enqueue(new Callback<List<Admin>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
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
}

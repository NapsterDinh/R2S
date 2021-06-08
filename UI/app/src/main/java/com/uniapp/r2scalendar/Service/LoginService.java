package com.uniapp.r2scalendar.Service;

import android.util.Log;

import com.uniapp.r2scalendar.Controller.ILoginController;
import com.uniapp.r2scalendar.Model.LoginRequest;
import com.uniapp.r2scalendar.Repository.LoginRepository;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.ILoginView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private ILoginController iLoginController;
    private ILoginView loginView;


    public LoginService(ILoginController iLoginController, ILoginView loginView) {
        this.iLoginController = iLoginController;
        this.loginView = loginView;
    }


    public void getAllTrainee(String Username, String Password) {
        try {
            LoginRepository loginRepository = RetrofitClient.Client().create(LoginRepository.class);
            Call<List<LoginRequest>> loginRequestCallGetAll = loginRepository.getAllTrainee(Username,Password);
            loginRequestCallGetAll.enqueue(new Callback<List<LoginRequest>>() {
                @Override
                public void onResponse(Call<List<LoginRequest>> call, Response<List<LoginRequest>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    loginView.disableProgressDialog();
                    iLoginController.onResult(response.body());
                }

                @Override
                public void onFailure(Call<List<LoginRequest>> call, Throwable t) {
                    iLoginController.onFailureResponse(t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllTrainer(String Username, String Password) {
        try {
            LoginRepository loginRepository = RetrofitClient.Client().create(LoginRepository.class);
            Call<List<LoginRequest>> loginRequestCallGetAll = loginRepository.getAllTrainer(Username,Password);
            loginRequestCallGetAll.enqueue(new Callback<List<LoginRequest>>() {
                @Override
                public void onResponse(Call<List<LoginRequest>> call, Response<List<LoginRequest>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    loginView.disableProgressDialog();
                    iLoginController.onResult(response.body());
                }

                @Override
                public void onFailure(Call<List<LoginRequest>> call, Throwable t) {
                    iLoginController.onFailureResponse(t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllAdmin(String Username, String Password) {
        try {
            LoginRepository loginRepository = RetrofitClient.Client().create(LoginRepository.class);
            Call<List<LoginRequest>> loginRequestCallGetAll = loginRepository.getAllAdmin(Username,Password);
            loginRequestCallGetAll.enqueue(new Callback<List<LoginRequest>>() {
                @Override
                public void onResponse(Call<List<LoginRequest>> call, Response<List<LoginRequest>> response) {
                    Log.e("Response Status", String.valueOf(response.code()));
                    loginView.disableProgressDialog();
                    iLoginController.onResult(response.body());
                }

                @Override
                public void onFailure(Call<List<LoginRequest>> call, Throwable t) {
                    iLoginController.onFailureResponse(t.getMessage());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

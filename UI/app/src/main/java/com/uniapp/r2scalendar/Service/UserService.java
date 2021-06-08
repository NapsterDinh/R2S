package com.uniapp.r2scalendar.Service;

import android.util.Log;

import com.uniapp.r2scalendar.Controller.IAccountController;
import com.uniapp.r2scalendar.Model.Trainee;
import com.uniapp.r2scalendar.Repository.TraineeRepository;
import com.uniapp.r2scalendar.Utils.RetrofitClient;
import com.uniapp.r2scalendar.View.ILoginView;
import com.uniapp.r2scalendar.View.ISignUpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    private IAccountController accountController;
    private ILoginView loginView;
    private ISignUpView signUpView;

    public UserService(IAccountController accountController, ILoginView loginView, ISignUpView signUpView) {
        this.accountController = accountController;
        this.loginView = loginView;
        this.signUpView = signUpView;
    }

    public void SignUp(Trainee user) {
        try {
            user.setUserName("cuong");
            TraineeRepository traineeRepository = RetrofitClient.Client().create(TraineeRepository.class);
            Call<Trainee> traineeCall = traineeRepository.insertTrainee(user);
            traineeCall.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    Log.e("Response Status", String.valueOf(response.code()));

                    signUpView.OnSignUpSuccess(response.body().getEmail());
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    signUpView.OnSignUpSuccess(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

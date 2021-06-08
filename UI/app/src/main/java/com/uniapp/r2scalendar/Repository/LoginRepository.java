package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginRepository {
    @GET("trainee/{username}/{password}")
    Call<List<LoginRequest>> getAllTrainee(@Path("username") String Username, @Path("password") String Password);

    @GET("trainer/{username}/{password}")
    Call<List<LoginRequest>> getAllTrainer(@Path("username") String Username, @Path("password") String Password);

    @GET("admin/{username}/{password}")
    Call<List<LoginRequest>> getAllAdmin(@Path("username") String Username, @Path("password") String Password);
}
